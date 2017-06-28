



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/bootstrap-table.min.css">
    <link rel="stylesheet" href="../css/button_style.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <!--<script src="assets/bootstrap2.3/js/bootstrap.min.js"></script>-->
    <script src="../js/jquery.base64.js"></script>
    <script src="../js/bootstrap-table.js"></script>
    <script src="../js/bootstrap-table-export.js"></script>
</head>
<body>
<div>
    <br><br><br>
    <p style="text-align:center;font-size: larger" class=" col-xs-2 ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;会议预定->看会议室</p>
    <p style="text-align:center;font-weight:bold;font-size: larger" class="col-xs-12 ">查看会议室</p>

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
           data-cache="false"
           data-height="517">
        <thead>
        <tr>
            <th data-field="meetingname" data-formatter="idFormatter">门牌编号</th>
            <th data-field="roomname">会议室名称</th>
            <th data-field="begintime">容纳人数</th>
            <th data-field="endtime">当前状态</th>
            <th data-field="operation">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
