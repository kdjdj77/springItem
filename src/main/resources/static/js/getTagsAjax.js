$(function() {
	$("select[name='category']").change(function(){
		cat = $("select[name='category']").val().trim();
		if (cat == "") $("#tagradio").html("");
		else loadTags(cat);
	})
})
function loadTags(cat) {
	const data = {
		"category":cat,
	};
	$.ajax({
		url: "data/tags",
		type: "GET",
		data: data,
		cache: false,
		success: function(data, status, xhr) {
			if (status == "success") {
				if (data.status != "OK") {
					alert(data.status);
					return;
				}
				buildTagList(data); // 비행편 목록 렌더링
			}
		},
	});	
}

function buildTagList(result) {
	const out = [];
	
	result.data.forEach(tag => {
		let id = tag.id;
		let name = tag.name;
		const row = `
			<div style="width:14rem; border:1px solid black; border-radius:5px; margin:0.5rem; padding:1rem;">
				<label for="tag\${id}">${name}</label>
				<input type="radio" style="float:right; width:25px; height:25px;" id="tag\${id}" name="tag" value="${id}" required>
			</div>
	        `;
		out.push(row);
	});
	$("#tagradio").html(out.join("\n"));
}