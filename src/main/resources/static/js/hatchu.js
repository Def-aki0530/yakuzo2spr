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




