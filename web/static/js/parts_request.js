var $table = $('#table'),
$remove = $('#remove'),
selections = [];

function initTable() {
  $table.bootstrapTable({
    height: getHeight(),
    columns: [
      [
        {
          title: '维修记录id',
          field: 'rrid',
          align: 'center',
          editable: false
        },
         {
          field: 'maintenance',
          title: '维修人员',
          editable: false,
          align: 'center'
        },
        {
          field: 'requiredPart',
          title: '所需备件信息',
          editable: false,
          align: 'center'
        },
        {
          field: 'operate',
          title: '操作',
          align: 'center',
          events: operateEvents,
          formatter: operateFormatter
        }
      ]
    ]
  });
  // sometimes footer render error.
  setTimeout(function () {
    $table.bootstrapTable('resetView');
  }, 200);
  $table.on('check.bs.table uncheck.bs.table ' +
  'check-all.bs.table uncheck-all.bs.table', function () {
    $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);

    // save your data, here just save the current page
    selections = getIdSelections();
    // push or splice the selections if you want to save all data selections
  });
  $table.on('expand-row.bs.table', function (e, index, row, $detail) {
    if (index % 2 == 1) {
      $detail.html('Loading from ajax request...');
      $.get('LICENSE', function (res) {
        $detail.html(res.replace(/\n/g, '<br>'));
      });
    }
  });
  $table.on('all.bs.table', function (e, name, args) {
    console.log(name, args);
  });
  $remove.click(function () {
    var ids = getIdSelections();
    $table.bootstrapTable('remove', {
      field: 'id',
      values: ids
    });
    $remove.prop('disabled', true);
  });
  $(window).resize(function () {
    $table.bootstrapTable('resetView', {
      height: getHeight()
    });
  });
}

function getIdSelections() {
  return $.map($table.bootstrapTable('getSelections'), function (row) {
    return row.id
  });
}

function responseHandler(res) {
  $.each(res.rows, function (i, row) {
    row.state = $.inArray(row.id, selections) !== -1;
  });
  return res;
}

function detailFormatter(index, row) {
  var html = [];
  $.each(row, function (key, value) {
    html.push('<p><b>' + key + ':</b> ' + value + '</p>');
  });
  return html.join('');
}

function operateFormatter(value, row, index) {
  return [
    '<a class="like" href="javascript:void(0)" title="save">',
      '<i class="glyphicon glyphicon-ok"></i>',
    '</a>  ',
    '<a class="remove" href="javascript:void(0)" title="Remove">',
    '<i class="glyphicon glyphicon-remove"></i>',
    '</a>'
  ].join('');
}

// var func_confirm = function() {};
var g_row;

window.operateEvents = {
  'click .like': function (e, value, row, index) {
    row.op = "confirm";
    g_row = row;
    $("#myModal").modal("show");
    // $('#confirm_modal').modal('show');
    // func_confirm = function() {
    //   $.post('customer_manage', row, function(data) {
    //     console.log(data);
    //     if(data.status) {
    //       $('#confirm_modal').modal('hide');
    //       $('#confirm_modal').find('.alert-field').html("");
    //     } else {
    //       var html = '<div class="alert alert-danger alert-dismissible fade in" role="alert">'+
    //       '<button type="button" class="close" data-dismiss="alert" '+
    //       'aria-label="Close"><span aria-hidden="true">×</span></button><p>'+
    //       data.error +
    //       '</p></div>';
    //       $('#confirm_modal').find('.alert-field').html(html);
    //     }
    //   });
    // }
  },
  'click .remove': function (e, value, row, index) {
    $('#confirm_modal').modal('show');
    func_confirm = function() {
      $.post('customer_manage', {op: "delete", id: row.id}, function(data) {
        if(data.status) {
          $table.bootstrapTable('remove', {
            field: 'rrid',
            values: [row.rrid]
          });
          $('#confirm_modal').find('.alert-field').html("");
          $('#confirm_modal').modal('hide');
        } else {
          var html = '<div class="alert alert-danger alert-dismissible fade in" role="alert">'+
          '<button type="button" class="close" data-dismiss="alert" '+
          'aria-label="Close"><span aria-hidden="true">×</span></button><p>'+
          data.error +
          '</p></div>';
          $('#confirm_modal').find('.alert-field').html(html);
        }
      });
    }
  }
};

function totalTextFormatter(data) {
  return 'Total';
}

function totalNameFormatter(data) {
  return data.length;
}

function totalPriceFormatter(data) {
  var total = 0;
  $.each(data, function (i, row) {
    total += +(row.price.substring(1));
  });
  return '$' + total;
}

function getHeight() {
  return $(window).height() - $('h1').outerHeight(true) - $('.navbar').outerHeight(true);
}

$(function () {
  var scripts = [
    './static/js/bootstrap-table.js',
    './static/js/tableExport.js',
    './static/js/bootstrap-table-editable.js',
    './static/js/bootstrap-editable/bootstrap-editable.js'
  ],
  eachSeries = function (arr, iterator, callback) {
    callback = callback || function () {};
    if (!arr.length) {
      return callback();
    }
    var completed = 0;
    var iterate = function () {
      iterator(arr[completed], function (err) {
        if (err) {
          callback(err);
          callback = function () {};
        }
        else {
          completed += 1;
          if (completed >= arr.length) {
            callback(null);
          }
          else {
            iterate();
          }
        }
      });
    };
    iterate();
  };

  eachSeries(scripts, getScript, initTable);
});

function getScript(url, callback) {
  var head = document.getElementsByTagName('head')[0];
  var script = document.createElement('script');
  script.src = url;

  var done = false;
  // Attach handlers for all browsers
  script.onload = script.onreadystatechange = function() {
    if (!done && (!this.readyState ||
      this.readyState == 'loaded' || this.readyState == 'complete')) {
        done = true;
        if (callback)
        callback();

        // Handle memory leak in IE
        script.onload = script.onreadystatechange = null;
      }
    };

    head.appendChild(script);

    // We handle everything using the script element injection
    return undefined;
  }

$('#add_submit').click(function() {
  $('#add_form').submit();
});
$('#add_form').on('submit', function(e) {
  e.preventDefault();
  var isValidForm = $('#add_form').data('formValidation').isValid();
  if(!isValidForm) {
    return;
  }
  var data = $(e.target).serialize();
  data += "&op=confirm";
  data = data+"&rrid="+g_row.rrid;
  $.post('parts_request', data, function(data) {
    if(!data.status) {
      var html = '<div class="alert alert-danger alert-dismissible fade in" role="alert">'+
        '<button type="button" class="close" data-dismiss="alert" '+
        'aria-label="Close"><span aria-hidden="true">×</span></button><p>'+
        data.error +
        '</p></div>';
      $('#add_form_modal').html(html);
    } else {
      $('#myModal').modal('hide');
      location.href = "delivery_list.html";
    }
  });
});

$(function() {
  $('.selectpicker-ajax').selectpicker({
    liveSearch: true
  })
  .ajaxSelectPicker({
    ajax: {
      url: "parts_request",
      data: function () {
        var params = {
          op: "getpname",
          search: '{{{q}}}'
        };
        return params;
      }
    }
  })
})

$(function() {
  $('#add_form').formValidation({
    framework: 'bootstrap',
    message: '输入不合法',
    icon: {
      valid: 'glyphicon glyphicon-ok',
      invalid: 'glyphicon glyphicon-remove',
      validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
      partName : {
        row: '.controls',
        validators: {
          notEmpty: {
            message: '备件名称是必填的'
          }
        }
      },
      quantity: {
        row: '.controls',
        validators: {
          notEmpty: {
            message: '出库数量是必填的'
          },
          regexp: {
            regexp: /^[1-9]\d*$/,
            message: '出库数量不合法'
          }
        }
      },
      price: {
        row: '.controls',
        validators: {
          notEmpty: {
            message: '出库单价是必填的'
          },
          regexp: {
            regexp: /^(?:[1-9][0-9]*(?:\.[0-9]+)?|0\.(?!0+$)[0-9]+)$/,
            message: '出库单价不合法'
          }
        }
      }
    }
  });
})
