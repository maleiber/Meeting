<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/bootstrap-table.min.css">
    <link rel="stylesheet" href="">
</head>
<body>
<div>
    <br><br><br>
    <p style="text-align:center;font-size: larger" class=" col-xs-2 ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;个人中心->我的会议</p>
    <p style="text-align:center;font-weight:bold;font-size: larger" class="col-xs-12 ">我要参加的会议</p>
</div>
    <div class="container">
    <table id="table"
           class="table table-striped"
           data-toggle="table"
           data-url="data/data1.json"
           data-show-columns="true"
           data-search="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-pagination="true"
           data-page-size="10"
           data-cache="false"
           data-height="517">
        <thead>
        <tr>
            <th data-field="meetingname" data-formatter="idFormatter">会议名称</th>
            <th data-field="roomname">会议室名称</th>
            <th data-field="begintime">会议开始时间</th>
            <th data-field="endtime">会议结束时间</th>
            <th data-field="resvertime">会议预定时间</th>
            <th data-field="resverman">预订者</th>
            <th class="col-xs-2"  data-field="operation" data-formatter="operateFormatter">操作</th>
        </tr>
        </thead>
    </table>
</div>
<script type="text/javascript">
    function operateFormatter(value, row, index) {
        return [
            '<button type="button" class=" RoleOfC btn btn-default  btn-sm" data-toggle="modal" data-target="#myModal">查看详情</button>',

        ].join('');
    }
</script>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    模态框（Modal）标题
                </h4>
            </div>
            <div class="modal-body">
                在这里添加一些文本
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<style type="text/css">
    .table>tbody>tr>td{
        vertical-align: middle!important;padding:3px;line-height:1.42857143;vertical-align:top;border-top:1px solid #ddd}
</style>
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<!--<script src="assets/bootstrap2.3/js/bootstrap.min.js"></script>-->
<script src="../js/jquery.base64.js"></script>
<script src="../js/bootstrap-table.js"></script>
<script src="../js/bootstrap-table-export.js"></script>
</body>
</html>
