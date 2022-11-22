$(function() {
	$("select[name='category']").change(function(){
		cat = $("select[name='category']").val().trim();
		if (cat == "") $("#tagradio").html("");
		else loadTags(cat);
	})
	$("#colorAdd").click(function(){
		$("#colors").append(`
			<div class="input-group mb-2" style="padding-right:30%; float:up">
			<input class="form-control" type="text" name="colors"/>
			<button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">삭제</button>
			</div>
		`);
	});
	$("#sizeAdd").click(function(){
		$("#sizes").append(`
			<div class="input-group mb-2" style="padding-right:30%; float:up">
			<input class="form-control" type="text" name="sizes"/>
			<button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">삭제</button>
			</div>
		`);
	});
	$("#btnAdd").click(function(){
		$("#files").append(`
			<div class="input-group mb-2">
			<input class="form-control col-xs-3" type="file" name="ifile"/>
			<button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">삭제</button>
			</div>
		`);
	});
	$("#btnAdd2").click(function(){
		$("#files2").append(`
			<div class="input-group mb-2">
			<input class="form-control col-xs-3" type="file" name="cfile"/>
			<button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">삭제</button>
			</div>
		`);
	});
	$("#submitBtn").click(function(){
		submitForm();
	});
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

function submitForm() {
	let colorCnt = $("input[name='colors']").length;
	let cnt = colorCnt;
	for(let i = 0; i < colorCnt; i++) if ($("input[name=colors]").eq(i).val().trim() == "") cnt--;
	colorCnt = cnt;
	
	let sizeCnt = $("input[name='colors']").length;
	cnt = sizeCnt;
	for(let i = 0; i < sizeCnt; i++) if ($("input[name=sizes]").eq(i).val().trim() == "") cnt--;
	sizeCnt = cnt;
	
	if (colorCnt == 0) {alert("색상을 추가해주세요"); return;}
	if (sizeCnt == 0) {alert("사이즈를 추가해주세요"); return;}
	
	submit();
}