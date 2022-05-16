/**
 *
 */

 function getPages(){
	//alert("move");
	let fd = new FormData(f1);
	let arrData = {};
	for(let value of fd.entries()){
		arrData[value[0]] = value[1];
	}

	$.post(
		"hatchugetpages",
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
					//alert(page);
					arrData["page"] = page;
					getList(arrData);
				}
			});
		}
	);
}

function getList(arrData){
	$.post(
		"hatchugetlist",
		arrData,
		function(data){
				//alert(data);
				$("#list").html(data);
		}
	);
}

let mdl1;
let mdl2;

$(function(){
	mdl1 = $('.modal1').modaal({type:'ajax'});
	mdl2 = $('.modal2').modaal({type:'ajax'});
});

function cancel(){
	mdl1.modaal("close");
	mdl2.modaal("close");
}

function searchTorihikisaki(){
	let fd = new FormData(frm);
	let arrData = {};
	for(let value of fd.entries()){
		arrData[value[0]] = value[1];
	}

	$.post(
		"t-sansho-getpage",
		arrData,
		function(data){
			alert(data);
		}
	);
}

function searchTorihikisaki(){
	//alert("call success");

	let fd = new FormData(frm);
	let arrData = {};
	for(let value of fd.entries()){
		arrData[value[0]] = value[1];
	}

	$.post(
		"t-sansho-getpage",
		arrData,
		function(data){
			//alert(data);
			if(data == 0){
				$(".pagination").twbsPagination("destroy");
			}
			$(".pagination").twbsPagination("destroy");
			$(".pagination").twbsPagination({
				totalPages : data,
				visiblePages : 5,
				onPageClick : function(event,page){
					arrData["page"] = page;
					torihikisakiGetList(arrData);
				}
			});

		}
	);

}

function torihikisakiGetList(arrData){
	//alert(arrData["page"]);
	$.post(
		"t-sansho-getlist",
		arrData,
		function(data){
			$("#list").html(data);
		}
	);
}

function choiceSpl(code,name){
	$("#torihikisaki_code").val(code);
	$("#torihikisaki_name").val(name);
	mdl1.modaal('close');
}
