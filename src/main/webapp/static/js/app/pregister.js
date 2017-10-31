/**
 * 
 */
// 配置模块
layui.config({
	base : '/static/js/',
	version: new Date().getTime()
}).extend({ // 模块别名
	jacommon : 'jacommon'
});

// 加载模块
layui.use(['form', 'layedit', 'jacommon', 'laydate', 'layer','element'], function() {
	var form = layui.form(),
		layer = layui.layer,
		jacommon = layui.jacommon,
		$ = layui.jquery,
		element = layui.element(),
		layedit = layui.layedit;
	
	//自定义验证规则
	form.verify({
		account : function(value) {
			if (value.length < 2) {
				return '账号至少得2个字符!';
			}
		},
		pass : [ /(.+){6,12}$/, '密码必须6到12位' ],
		telphone:[/(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$/, '电话不合法']
	});

	//表单提交
	form.on('submit(btnSub)', function(data) {
		var postUrl = data.form.action,
			postData = data.field,
			subBtn = $(data.elem),
			btnText = subBtn.html();
			if (postData.open !== 'undefined' && postData.open !== '' && postData.open !== null && postData.open == 'on') {
				postData.isEnable = 1;
			} else {
				postData.isEnable = 0;
			}
			
			subBtn.addClass('layui-btn-disabled').html('<i class="layui-icon">&#xe605;</i> 提交中...');
//			// 提交请求
			jacommon.ajaxPost(postUrl, postData, function(res) {
			
			jacommon.msgOffsetSuccess('保存成功', function() {
				// 关闭当前窗口
				window.location.href = WEB_ROOT + '/login';
			});
		}, function(res) {
			jacommon.msgOffsetError('保存失败，' + res.message + '(错误代码：' + res.code + ')');
		}, function() {
			subBtn.removeClass('layui-btn-disabled').html(btnText);
		});
			
		return false;
	});

	$(document).on('click','#btnBack', function(){

		parent.location.reload(); // 父页面刷新
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.layer.close(index);
	});
	
});