/**
 *
 */

 function kensaku(){
	let arrData = {
		shain : $("#shain").val(),
		login_flg : $("#login_flg").val(),
		kengen_code : $("#kengen_code").val(),
		delete_flg : $("#delete_flg").val()
	}

	$.post(
		"shaingetpages",
		arrData,
		function(data){
			//alert(data);
			if(data == 0){
				$(".pagination").twbsPagination("destroy");
			}
			$(".pagination").twbsPagination("destroy");
			$(".pagination").twbsPagination({
				totalPages: data,
				visiblePages: 5,
				onPageClick: function(event,page){
					alert(page);
					arrData["page"] = page;
					getList(arrData);
				}
			});
		}
	);
}

function getList(arrData){
	$.post(
		"shaingetlist",
		arrData,
		function(data){
			$("#list").html(data);
		}
	)
}

function cancel(){
	if(window.confirm("登録を中断して一覧画面に戻ります。よろしいですか？")){
		location.href = "mst_shain";
	}
}