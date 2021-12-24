<%@ page import="entity.Filestatus" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.Dicdao" %>
<%@ page import="entity.EntityDic" %>
<%@ page import="dao.Filedao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>字典操作</title>
    <meta name="description" content="Kenny is a Dashboard & Admin Site Responsive Template by hencework.">
    <meta name="keywords" content="admin, admin dashboard, admin template, cms, crm, Kenny Admin, kennyadmin, premium admin templates, responsive admin, sass, panel, software, ui, visualization, web app, application">
    <meta name="author" content="hencework">
    <!-- vector map CSS -->
    <link href="static/css/jquery-jvectormap-2.0.2.css" rel="stylesheet" type="text/css">

    <!-- Footable CSS -->
    <link href="static/css/footable.bootstrap.min.css" rel="stylesheet" type="text/css">
    <!-- Custom CSS -->
    <link href="static/css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="wrapper">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <a id="toggle_nav_btn" class="toggle-left-nav-btn inline-block mr-20 pull-left" href="javascript:void(0);"><i class="fa fa-bars"></i></a>
        <a href="index.jsp"><img class="brand-img pull-left" src="static/picture/logo.png" alt="brand"></a>
    </nav>
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
                    <h5 class="txt-light">字典详细信息</h5>
                </div>
            </div>
            <!-- /Title -->
            <!-- Row -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-default card-view">
                        <div class="panel-heading">
                            <div class="pull-left">
                                <h6 class="panel-title txt-dark">编辑字典</h6>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="panel-wrapper collapse in">
                            <div class="panel-body">
                                <div class="table-wrap">
                                    <table id="footable_2" class="table" data-paging="true" data-filtering="true" data-sorting="true">
                                        <thead>
                                        <tr>
                                            <th data-name="id" data-breakpoints="xs" data-type="number">ID</th>
                                            <th data-name="firstName">字段名</th>
                                            <th data-name="lastName">字段类型</th>
                                            <th data-name="jobTitle">备注</th>
                                            <th data-name="forthName">主外键属性</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            Dicdao dao = new Dicdao();
                                            String tablename = (String)request.getAttribute("tablename");
                                            ArrayList<EntityDic> list = (ArrayList<EntityDic>)dao.selectTll(tablename);
                                            for (EntityDic bi : list) {
                                        %>
                                        <tr data-expanded="true">
                                                <td><%=String.valueOf(bi.getId())%></td>
                                                <td><%=bi.getFirstname()%></td>
                                                <td><%=bi.getThirdname()%></td>
                                                <td><%=bi.getSecondname()%></td>
                                                <td><%=bi.getForthname()%></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                        </tbody>
                                    </table>

                                    <!--Editor-->
                                    <div class="modal fade" id="editor-modal" tabindex="-1" role="dialog" aria-labelledby="editor-title">
                                        <div class="modal-dialog" role="document">
                                            <form action="/UpdateDicServlet" class="modal-content form-horizontal" id="editor">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                                    <h5 class="modal-title" id="editor-title">Add Row</h5>
                                                </div>
                                                <div class="modal-body">
                                                    <form action="/UpdateDicServlet" method="post">
                                                    <input type="text" id="id" name="id" class="hidden">
                                                    <input type="text" id="tablename" name="tablename" class="hidden" value="<%=tablename%>">
                                                    <div class="form-group required">
                                                        <label for="firstName" class="col-sm-3 control-label">字段名</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="字段名" required="">
                                                        </div>
                                                    </div>
                                                    <div class="form-group required">
                                                        <label for="lastName" class="col-sm-3 control-label">字段类型</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="字段类型" required="">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="jobTitle" class="col-sm-3 control-label">备注</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" id="jobTitle" name="jobTitle" placeholder="备注">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="forthName" class="col-sm-3 control-label">备注</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" id="forthName" name="forthName" placeholder="备注">
                                                        </div>
                                                    </div>
                                                    <button type="submit" class="btn btn-primary">保存</button>
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                                    </form>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <!--/Editor-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /Row -->
        </div>

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
    <!-- /Main Content -->

</div>
<!-- /#wrapper -->

<!-- JavaScript -->

<!-- jQuery -->
<script src="static/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="static/js/bootstrap.min.js"></script>

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
