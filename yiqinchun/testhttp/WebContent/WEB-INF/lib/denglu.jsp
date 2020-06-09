<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户注册</title>

<!-- Bootstrapvalidate导入 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/plug-ins/css/bootstrapValidator.css" />
<link href="${pageContext.request.contextPath}/plug-ins/css/demo.css" rel="stylesheet" type="text/css">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/plug-ins/css/bootstrap.css" rel="stylesheet">

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="${pageContext.request.contextPath}/plug-ins/js/jquery-1.10.2.min.js"></script>

<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="${pageContext.request.contextPath}/plug-ins/js/bootstrap.js"></script>

<!-- validate表单验证导入 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-ins/js/bootstrapValidator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-ins/js/bootstrapValidator_zh_CN.js"></script>

<!-- Font Awesome -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-ins/css/font-awesome.min.css">

<script type="text/javascript">
	$(function() {
		$('#register').bootstrapValidator(
				{
					message : 'This value is not valid',
					feedbackIcons : {
						valid : 'glyphicon glyphicon-ok',
						invalid : 'glyphicon glyphicon-remove',
						validating : 'glyphicon glyphicon-refresh'
					},
					fields : {
						name : {
							validators : {
								notEmpty : {
									message : '账号不能为空'
								},
								stringLength : {
									min : 6,
									max : 12,
									message : '账号由6-12个字符组成'
								},
								regexp : {
									regexp : /^[a-zA-Z][a-zA-Z0-9]+$/,
									message : '账号只能由字母数字组成，必须由字母开头'
								}
							}
						},
						password:{
							validators:{
								notEmpty : {
									message : '密码不能为空'
								},
								stringLength : {
									min : 6,
									max : 6,
									message : '密码必须由6个字母数字组成'
								},
								regexp : {
									regexp : /^[a-zA-Z0-9]+$/,
									message : '密码只能由字母数字组成'
								}
							}
						},
						realname:{
							validators:{
								notEmpty : {
									message : '姓名不能为空'
								}
							}
						},
						number:{
							validators:{
								notEmpty : {
									message : '学号不能为空'
								},
								stringLength : {
									min : 8,
									max : 8,
									message : '学号必须由8位字符组成'
								},
								regexp : {
									regexp : /^2018(\d{4})+$/,
									message : '请输入正确的学号'
								}
							}
						},
						email:{
							validators:{
								notEmpty : {
									message : '邮箱不能为空'
								},
								regexp : {
									regexp : /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
									message : '请输入正确的邮箱格式'
								}
							}
						}
					},
					password1:{
						validators:{
							notEmpty : {
								message : '学院不能为空'
							},
							stringLength : {
								min : 15,
								max : 15,

							},
							regexp : {
								regexp : /^[a-zA-Z0-9]+$/,
								message : '学院只能由字母数字组成'
							}
						}
					},
					password2:{
						validators:{
							notEmpty : {
								message : '所在系不能为空'
							},
							stringLength : {
								min : 6,
								max : 6,
							},
							regexp : {
								regexp : /^[a-zA-Z0-9]+$/,
								message : '所在系只能由字母数字组成'
							}
						}
					},
					password3:{
						validators:{
							notEmpty : {
								message : '所在班级不能为空'
							},
							stringLength : {
								min : 6,
								max : 6,
							},
							regexp : {
								regexp : /^[a-zA-Z0-9]+$/,
								message : '所在班级只能由字母数字组成'
							}
						}
					},
					password4:{
						validators:{
							notEmpty : {
								message : '生源地不能为空'
							},
							stringLength : {
								min : 6,
								max : 6,
							},
							regexp : {
								regexp : /^[a-zA-Z0-9]+$/,
								message : '生源地只能由字母数字组成'
							}
						}
					},
					password5:{
						validators:{
							notEmpty : {
								message : '备注不能为空'
							},
							stringLength : {
								min : 6,
								max : 6,
							},
							regexp : {
								regexp : /^[a-zA-Z0-9]+$/,
								message : '备注只能由字母数字组成'
							}
						}
					},
				});
	});
</script>

</head>
<body>
         <div class="col-lg-8 col-lg-offset-2">
             <div class="page-header">
                 <h2>用户注册</h2>
             </div>

             <form id="register" method="post" class="form-horizontal" action="${pageContext.request.contextPath }/registerServlet">
                 <div class="form-group">
                     <label class="col-lg-3 control-label">登陆账号:</label>
                     <div class="col-lg-4">
                         <input type="text" class="form-control" name="name" placeholder="请设置账号 " />
                     </div>
                 </div>

                 <div class="form-group">
                     <label class="col-lg-3 control-label">登陆密码:</label>
                     <div class="col-lg-5">
                         <input type="password" class="form-control" name="password" placeholder="请设置登陆密码" />
                     </div>
                 </div>
                 
                  <div class="form-group">
                     <label class="col-lg-3 control-label">姓名:</label>
                     <div class="col-lg-5">
                         <input type="text" class="form-control" name="realname" placeholder="请输入姓名" />
                     </div>
                 </div>
                 
                  <div class="form-group">
                     <label class="col-lg-3 control-label">性别:</label>
                     <div class="col-lg-5">
                        <input type="radio" name="sex" value="man" checked="checked"/> 男
                        <input type="radio" name="sex" value="woman" /> 女
                     </div>
                 </div>

                 <div class="form-group">
                     <label class="col-lg-3 control-label">学号:</label>
                     <div class="col-lg-5">
                         <input type="text" class="form-control" name="number"  placeholder="请输入学号"/>
                     </div>
                 </div>
                 
                 <div class="form-group">
                     <label class="col-lg-3 control-label">邮箱:</label>
                     <div class="col-lg-5">
                         <input type="text" class="form-control" name="email" placeholder="请输入用户邮箱"/>
                     </div>
                 </div>
                 
                 <div class="form-group">
                     <label class="col-lg-3 control-label">所在学院:</label>
                     <div class="col-lg-5">
                         <input type="text" class="form-control" name="password1" placeholder="请输入所在学院"/>
                     </div>
                 </div>
                 
                 <div class="form-group">
                     <label class="col-lg-3 control-label">所在系:</label>
                     <div class="col-lg-5">
                         <input type="text" class="form-control" name="password2" placeholder="请输入所在系"/>
                     </div>
                 </div>
                 
                 <div class="form-group">
                     <label class="col-lg-3 control-label">所在班级:</label>
                     <div class="col-lg-5">
                         <input type="text" class="form-control" name="password3" placeholder="请输入所在班级"/>
                     </div>
                 </div>
                 
                 
                 <div class="form-group">
                     <label class="col-lg-3 control-label">年份:</label>
                     <div class="col-lg-5">
						<select id="" name="year">
							<option value="">--入学年份（届）--</option>
							<option value="2016">2016</option>
							<option value="2017">2017</option>
							<option value="2018">2018</option>
							<option value="2019">2019</option>
						</select>
                     </div>
                 </div>

<div class="form-group">
                     <label class="col-lg-3 control-label">生源地:</label>
                     <div class="col-lg-5">
                         <input type="text" class="form-control" name="password4" placeholder="请输入生源地"/>
                     </div>
                 </div>
                 
                 <div class="form-group">
                     <label class="col-lg-3 control-label">备注:</label>
                     <div class="col-lg-5">
                         <input type="text" class="form-control" name="password5" placeholder="请输入备注"/>
                     </div>
                 </div>

                 <div class="form-group">
                     <div class="col-lg-3 col-lg-offset-3">
                         <button type="submit" class="btn btn-primary">注册</button>
                 
             </form>
         </div>
</body>
</html>