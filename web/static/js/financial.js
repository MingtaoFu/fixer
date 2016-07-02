var $table = $('#table'),
$remove = $('#remove'),
selections = [];

function initTable() {
  $table.bootstrapTable({
    height: getHeight(),
    columns: [
        {
          field: 'state',
          checkbox: true,
          rowspan: 1,
          align: 'center',
          valign: 'middle'
        }, {
          title: 'sid',
          field: 'id',
          align: 'center',
        // },
        // {
        //   title: 'rrid',
        //   field: 'id',
        //   align: 'center',
        // },
        {
          field: 'rrid',
          title: ' 维修记录',
          editable: false,
          align: 'center',
          // editable: {
          //   validate: function (value) {
          //     value = $.trim(value);
          //     if (!value) {
          //       return 'This field is required';
          //     }
          //     var data = $table.bootstrapTable('getData'),
          //     index = $(this).parents('tr').data('index');
          //     console.log(data[index]);
          //     return '';
          //   }
          // }
        },
        {
          field: 'laborCosts',
          title: '人工费',
          editable:false 
          // {
          //   validate: function (value) {
          //     value = $.trim(value);
          //     if (!value) {
          //       return 'This field is required';
          //     }
          //     if (!/^1[0-9]{10}$/.test(value)) {
          //       return '请输入合法的手机';
          //     }
          //     var data = $table.bootstrapTable('getData'),
          //     index = $(this).parents('tr').data('index');
          //     console.log(data[index]);
          //     return '';
          //   }
          // }
          ,
          align: 'center'
        },
        {
          field: 'materialsCosts',
          title: '材料费',
          editable: false
          // {
          //   validate: function (value) {
          //     value = $.trim(value);
          //     if (!/^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/.test(value) && value != '') {
          //       return '请输入合法email';
          //     }
          //     var data = $table.bootstrapTable('getData'),
          //     index = $(this).parents('tr').data('index');
          //     console.log(data[index]);
          //     return '';
          //   }
          // }
          ,
          align: 'center'
        },
        {
          field: 'warrantyPromise',
          title: '报修承诺',
          editable: false,
          // {
            // type: 'select',
            // source: [
            //   {value: 0, text: '家庭用户'},
            //   {value: 1, text: '单位用户'},
            //   {value: 2, text: '代理商'},
            //   {value: 3, text: '签约用户'}
            // ]
          // },
          align: 'center'
        },
        {
          field: 'notice',
          title: '注意事项',
          editable: false,
          align: 'center'
        },
        {
          field: 'settlementTime',
          title: '结算日期',
           editable: false,
           // {
          //   validate: function (value) {
          //     value = $.trim(value);
          //     if (!/^[0-9]*[-,0-9][0-9]*$/.test(value) && value != '') {
          //       return '请输入合法的电话';
          //     }
          //     var data = $table.bootstrapTable('getData'),
          //     index = $(this).parents('tr').data('index');
          //     console.log(data[index]);
          //     return '';
          //   }
          // },
          align: 'center'
        },
        // {
        //   field: 'addr',
        //   title: '地址',
        //   align: 'center',
        //   editable: true,
        // },
        // {
        //   field: 'zipCode',
        //   title: '邮编',
        //   align: 'center',
        //   editable: {
        //     validate: function (value) {
        //       value = $.trim(value);
        //       if (!/^[0-9]{6}$/.test(value) && value != '') {
        //         return '请输入合法的邮编';
        //       }
        //       var data = $table.bootstrapTable('getData'),
        //       index = $(this).parents('tr').data('index');
        //       console.log(data[index]);
        //       return '';
        //     }
        //   }
        // },
        // {
        //   field: 'citizenId',
        //   title: '身份证',
        //   align: 'center',
        //   editable: {
        //     type: 'text',
        //     title: '身份证',
        //     validate: function (value) {
        //       value = $.trim(value);
        //       if (!value) {
        //         return 'This field is required';
        //       }
        //       if (!/^[0-9]{17}([0-9,x])$/.test(value)) {
        //         return '请输入合法身份证号';
        //       }
        //       var data = $table.bootstrapTable('getData'),
        //       index = $(this).parents('tr').data('index');
        //       console.log(data[index]);
        //       return '';
        //     }
        //   },
        // },
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
    '<a class="expense" href="javascript:void(0)" title="expense">',
      '<i class="glyphicon glyphicon-yen"></i>',
    '</a>  '
    // '<a class="like" href="javascript:void(0)" title="repair">',
    //   '<i class="glyphicon glyphicon-wrench"></i>',
    // '</a>  ',
    // '<a class="remove" href="javascript:void(0)" title="Remove">',
    // '<i class="glyphicon glyphicon-remove"></i>',
    // '</a>'
  ].join('');
}

var func_confirm = function() {};

window.operateEvents = {
  'click .expense': function (e, value, row, index) {
    row.op = "update";
    $('#confirm_modal').modal('show');
    func_confirm = function() {
      $.post('customer_manage', row, function(data) {
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
  // 'click .remove': function (e, value, row, index) {
  //   $('#confirm_modal').modal('show');
  //   func_confirm = function() {
  //     $.post('customer_manage', {op: "delete", id: row.id}, function(data) {
  //       if(data.status) {
  //         $table.bootstrapTable('remove', {
  //           field: 'id',
  //           values: [row.id]
  //         });
  //         $('#confirm_modal').find('.alert-field').html("");
  //         $('#confirm_modal').modal('hide');
  //       } else {
  //         var html = '<div class="alert alert-danger alert-dismissible fade in" role="alert">'+
  //         '<button type="button" class="close" data-dismiss="alert" '+
  //         'aria-label="Close"><span aria-hidden="true">×</span></button><p>'+
  //         data.error +
  //         '</p></div>';
  //         $('#confirm_modal').find('.alert-field').html(html);
  //       }
  //     });
  //   }
  // }
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
    'http://rawgit.com/hhurz/tableExport.jquery.plugin/master/tableExport.js',
    './static/js/bootstrap-table-editable.js',
    'http://rawgit.com/vitalets/x-editable/master/dist/bootstrap3-editable/js/bootstrap-editable.js'
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
  $.post('customer_manage', data, function(data) {
    if(!data.status) {
      var html = '<div class="alert alert-danger alert-dismissible fade in" role="alert">'+
        '<button type="button" class="close" data-dismiss="alert" '+
        'aria-label="Close"><span aria-hidden="true">×</span></button><p>'+
        data.error +
        '</p></div>';
      $('#add_form_modal').html(html);
    } else {
      $('#myModal').modal('hide')
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
      customer_name: {
        row: '.controls',
        validators: {
          notEmpty: {
            message: '客户名是必填的'
          }
        }
      },
      mobile_phone: {
        row: '.controls',
        validators: {
          notEmpty: {
            message: '手机是必填的'
          },
          regexp: {
            regexp: /^1[0-9]{10}$/,
            message: '手机不合法'
          }
        }
      },
      citizen_id: {
        row: '.controls',
        validators: {
          notEmpty: {
            message: '身份证号是必填的'
          },
          regexp: {
            regexp: /^[0-9]{17}([0-9,x])$/,
            message: '身份证号不合法'
          }
        }
      },
      company_phone: {
        row: '.controls',
        validators: {
          regexp: {
            regexp: /^[0-9]*[-,0-9][0-9]*$/,
            message: '单位电话不合法'
          }
        }
      },
      zip_code: {
        row: '.controls',
        validators: {
          regexp: {
            regexp: /^[0-9]{6}$/,
            message: '邮编不合法'
          }
        }
      },
      email: {
        row: '.controls',
        validators: {
          emailAddress: {
            message: '请输入有效的电子邮件地址'
          }
        }
      }
    }
  });
});
