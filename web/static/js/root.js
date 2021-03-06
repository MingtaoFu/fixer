var $table = $('#table'),
$remove = $('#remove'),
selections = [];

function initTable() {
  $table.bootstrapTable({
    height: getHeight(),
    columns: [
      [
        {
          title: 'ID',
          field: 'uid',
          align: 'center',
        },
        {
          field: 'userName',
          title: '员工姓名',
          editable: true,
          align: 'center',
          editable: {
            validate: function (value) {
              value = $.trim(value);
              if (!value) {
                return 'This field is required';
              }
              var data = $table.bootstrapTable('getData'),
              index = $(this).parents('tr').data('index');
              console.log(data[index]);
              return '';
            }
          }
        },
        {
          field: 'password',
          title: '密码',
          editable: {
            type: "password",
            validate: function (value) {
              value = $.trim(value);
              if (!value) {
                return 'This field is required';
              }
              if (!/^[_0-9a-zA-Z]{6,16}$/.test(value)) {
                return '密码为6-16位数字、字母、下划线的组合';
              }
              var data = $table.bootstrapTable('getData'),
              index = $(this).parents('tr').data('index');
              return '';
            }
          },
          align: 'center'
        },
        {
          field: 'characters',
          title: '员工类别',
          editable: {
            type: 'select',
            source: [
              {value: 1, text: '客服'},
              {value: 2, text: '任务调度'},
              {value: 3, text: '技术工程师'},
              {value: 4, text: '财务人员'},
              {value: 5, text: '库管'},
              {value: 6, text: '运营监督'},
            ]
          },
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

var func_confirm = function() {};

window.operateEvents = {
  'click .like': function (e, value, row, index) {
    row.op = "update";
    console.log(row);
    $('#confirm_modal').modal('show');
    func_confirm = function() {
      $.post('staff_manage', row, function(data) {
        console.log(data);
        if(data.status) {
          $('#confirm_modal').modal('hide');
          $('#confirm_modal').find('.alert-field').html("");
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
  },
  'click .remove': function (e, value, row, index) {
    $('#confirm_modal').modal('show');
    func_confirm = function() {
      $.post('staff_manage', {op: "delete", uid: row.uid}, function(data) {
        if(data.status) {
          $table.bootstrapTable('remove', {
            field: 'uid',
            values: [row.uid]
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
  data += "&op=add";
  $.post('staff_manage', data, function(data) {
    if(!data.status) {
      var html = '<div class="alert alert-danger alert-dismissible fade in" role="alert">'+
        '<button type="button" class="close" data-dismiss="alert" '+
        'aria-label="Close"><span aria-hidden="true">×</span></button><p>'+
        data.error +
        '</p></div>';
      $('#add_form_modal').html(html);
    } else {
      $('#myModal').modal('hide');
      $table.bootstrapTable('refresh',{slient:true});
    }
  });
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
      userName: {
        row: '.controls',
        validators: {
          notEmpty: {
            message: '员工姓名是必填的'
          }
        }
      },
      password: {
        row: '.controls',
        validators: {
          notEmpty: {
            message: '密码是必填的'
          },
          regexp: {
            regexp: /^[\w]{6,16}$/,
            message: '密码必须为6-16位的字母、数字或下划线'
          }
        }
      }
    }
  });
});
