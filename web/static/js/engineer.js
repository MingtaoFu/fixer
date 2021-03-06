var $table = $('#table'),
$remove = $('#remove'),
selections = [];

function initTable() {
  $table.bootstrapTable({
    height: getHeight(),
    columns: [
        {
          title: '维修记录id',
          field: 'rrid',
          editable: false,
          align: 'center',
        },
        {
          field: 'did',
          title: '设备id',
          editable: false,
          align: 'center',
        },
        {
          field: 'status',
          title: '维修状态',
          editable: {
            // disabled:true
            type: 'select',
            source: [
        //      {value: 0, text: '未分配'},
              {value: 1, text: '分配未检测'},
              {value: 2, text: '检测完成维修未完成'},
              {value: 3, text: '维修完成'}
            ]
          },
          align: 'center'
        },
        {
          field: 'distributeTime',
          title: '分配时间',
          editable: false,
          align: 'center'
        },
        {
          field: 'maintenance',
          title: '维修人员',
          editable: false,
          align: 'center'
        },
        {
          field: 'detectionRecord',
          title: '检测记录',
          editable: true,
          align: 'center'
        },
       {
          field: 'repairRecord',
          title: '维修记录',
          editable: true,
          align: 'center'
        },
        {
          field: 'repairTime',
          title: '维修检测时间',
          editable: {
            type: 'datetime'
          },
          align: 'center'
        },
        {
          field: 'workload',
          title: '工作量',
          editable: true,
          align: 'center'
        },
        {
          field: 'requiredPart',
          title: '所需器件',
          editable: true,
          align: 'center'
        },
        {
          field: 'delayDegree',
          title: '延迟程度',
          editable: {
            type: 'select',
            source: [
              {value: 0, text: '一般'},
              {value: 1, text: '比较严重'},
              {value: 2, text: '严重'}
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
    '<a class="ok" href="javascript:void(0)" title="Ok">',
      '<i class="glyphicon glyphicon-ok"></i>',
    '</a>  ',
    '<a class="request" href="javascript:void(0)" title="request">',
      '<i class="glyphicon glyphicon-wrench"></i>',
    '</a>  '
    // ,
    // '<a class="remove" href="javascript:void(0)" title="Remove">',
    // '<i class="glyphicon glyphicon-remove"></i>',
    // '</a>'
  ].join('');
}


var func_confirm = function() {};

window.operateEvents = {
  'click .ok': function (e, value, row, index) {
    row.op = "update";
    $('#confirm_modal').modal('show');
    func_confirm = function() {
      $.post('engineer', row, function(data) {
        console.log(data);
        console.log(row);
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
  'click .request': function (e, value, row, index) {
    // $.post('engineer', {op: "request", id: row.id}, function(data) {
    //   if(data.status) {
    //     $table.bootstrapTable('remove', {
    //       field: 'id',
    //       values: [row.id]
    //     });
    //   }
    // });
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
    // $('.form-datetime').datetimepicker("render");
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
  var data = $(e.target).serialize();
  data += "&op=add";
  $.post('customer_manage', data, function(data) {
    console.log(data);
  });
})
