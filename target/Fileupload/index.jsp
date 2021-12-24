<%--
  Created by IntelliJ IDEA.
  User: JUNE
  Date: 2021/12/7
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="dao.FileNamedao" %>
<%@ page import="entity.Filestatus" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>文件上传</title>
    <meta name="description" content="文件上传">
    <meta name="keywords" content="admin, admin dashboard, admin template, cms, crm, Kenny Admin, kennyadmin, premium admin templates, responsive admin, sass, panel, software, ui, visualization, web app, application">
    <meta name="author" content="hencework">
    <!-- vector map CSS -->
    <link href="static/css/jquery-jvectormap-2.0.2.css" rel="stylesheet" type="text/css">
    <!-- Bootstrap Dropify CSS -->
    <link href="static/css/dropify.min.css" rel="stylesheet" type="text/css">
    <!-- Footable CSS -->
    <link href="static/css/footable.bootstrap.min.css" rel="stylesheet" type="text/css">
    <!-- Custom CSS -->
    <link href="static/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<!--Preloader-->
<div class="preloader-it">
    <div class="la-anim-1"></div>
</div>
<!--/Preloader-->
<div class="wrapper">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <a id="toggle_nav_btn" class="toggle-left-nav-btn inline-block mr-20 pull-left" href="javascript:void(0);"><i class="fa fa-bars"></i></a>
        <a href="index.jsp"><img class="brand-img pull-left" src="static/picture/logo.png" alt="brand"></a>
    </nav>
    <!-- /Top Menu Items -->
    <div class="fixed-sidebar-left">
        <ul class="nav navbar-nav side-nav nicescroll-bar">
            <li>
                <a class="active" href="index.jsp" data-toggle="collapse" data-target="#table_dr"><i class="icon-list mr-10"></i>文件上传<span class="pull-right"><i class="fa fa-fw fa-angle-down"></i></span></a>
            </li>
            <li>
                <a class="active" href="tableshow.jsp" data-toggle="collapse" data-target="#table_dr"><i class="icon-list mr-10"></i>数据表查看<span class="pull-right"><i class="fa fa-fw fa-angle-down"></i></span></a>
            </li>
        </ul>
    </div>
    <!-- /Left Sidebar Menu -->
    <!-- Main Content -->
    <div class="page-wrapper">
        <div class="container-fluid">
            <!-- Title -->
            <div class="row heading-bg bg-green">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h5 class="txt-light">文件上传</h5>
                </div>
            </div>
            <!-- /Title -->
            <!-- Row -->
            <div class="row">
            <!-- Basic Table -->
            <div class="col-sm-12">
                <div class="panel panel-default card-view">
                    <div class="panel-heading">
                        <div class="pull-left">
                            <h6 class="panel-title txt-dark">文件上传详细信息</h6>
                        </div>
                    </div>
                    <div class="panel-wrapper collapse in">
                        <div class="panel-body">
                            <div class="row">
                                <form action="/UploadHandleServlet" enctype="multipart/form-data" method="post" >
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <div class="col-md-9">
                                                <input type="text" class="form-control" placeholder="Uploader" name="username">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mt-40">
                                        <input type="file" id="input-file-now" class="dropify" name="file1">
                                    </div>
                                    <button class="btn btn-success btn-anim"><i class="icon-rocket"></i><span class="btn-text">上传</span></button>
                                </form>
                            </div>
                            <div class="table-wrap mt-40">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered mb-0">
                                        <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>文件名</th>
                                            <th>文件类型</th>
                                            <th>上传者</th>
                                            <th>读取状态</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            FileNamedao dao = new FileNamedao();
                                            ArrayList<Filestatus> list = (ArrayList<Filestatus>)dao.Selectingd();
                                            for (Filestatus bi : list) {
                                                boolean f=bi.getFilestatus();
                                                String status=null;
                                        %>
                                        <tr>
                                            <form action="/" method="post">
                                                <td><%=bi.getId()%></td>
                                                <td><%=bi.getFilename()%></td>
                                                <td><%=bi.getFiletype()%></td>
                                                <td><%=bi.getUploader()%></td>
                                                <td><%
                                                        if(f){
                                                            status="已读取";
                                                            out.print("<span class=\"label label-success\">"+status+"</span>");
                                                        }else{
                                                            status="未读取";
                                                            out.print("<span class=\"label label-info\">"+status+"</span>");
                                                        }
                                                    %></td>
                                                <td>
                                                    <button class="btn btn-success">
                                                        <%
                                                            if(f){
                                                                out.print("<a href=\"tableshow.jsp\">查看详情</a>");
                                                            }else{
                                                                out.print("<a href=/ReadFileServlet?id="+bi.getId()+">读取文件</a>");
                                                            }
                                                        %>
                                                    </button>
                                                    <button class="btn btn-info" onclick="if(confirm('是否确定删除？')===false)return false"><a href="/DeleteFileServlet?id=<%=bi.getId()%>">删除</a></button>
                                                </td>
                                            </form>
                                        </tr>
                                        <%
                                            }
                                        %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /Basic Table -->
        </div>
            <!-- /Row -->
            <!-- Footer -->
            <footer class="footer container-fluid pl-30 pr-30">
                <div class="row">
                    <div class="col-sm-5">
                        <a href="index.html" class="brand mr-30"><img src="static/picture/logo-sm.png" alt="logo"></a>
                        <ul class="footer-link nav navbar-nav">
                            <li class="logo-footer"><a href="#">help</a></li>
                            <li class="logo-footer"><a href="#">terms</a></li>
                            <li class="logo-footer"><a href="#">privacy</a></li>
                        </ul>
                    </div>
                    <div class="col-sm-7 text-right">
                        <p>Copyright &copy; 2021.Company name All rights reserved.<a target="_blank" href="https://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
                    </div>
                </div>
            </footer>
            <!-- /Footer -->
        </div>
    </div>
    <!-- /Main Content -->
</div>
<!-- /#wrapper -->
<!-- JavaScript -->
<!-- jQuery -->
<script src="static/js/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="static/js/bootstrap.min.js"></script>
<!-- Bootstrap Daterangepicker JavaScript -->
<script src="static/js/dropify.min.js"></script>

<!-- Form Flie Upload Data JavaScript -->
<script src="static/js/form-file-upload-data.js"></script>

<!-- Data table JavaScript -->
<script src="static/js/moment.min.js"></script>
<script src="static/js/footable.min.js" type="text/javascript"></script>
<script src="static/js/footable-data.js"></script>
<!-- Slimscroll JavaScript -->
<script src="static/js/jquery.slimscroll.js"></script>
<!-- Fancy Dropdown JS -->
<script src="static/js/dropdown-bootstrap-extended.js"></script>
<!-- Init JavaScript -->
<script src="static/js/init.js"></script>
<%
    String message = (String)request.getAttribute("message");   //获取错误信息
    if(message != null) {
%>
<script type="text/javascript" >
    alert("<%=message%>");
</script>
<%
    }
%>
</body>
</html>