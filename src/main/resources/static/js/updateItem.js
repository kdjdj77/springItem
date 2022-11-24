$(function() {
	$("select[name='category']").change(function(){
		cat = $("select[name='category']").val().trim();
		if (cat == "") $("#tag").html("");
		else loadTags(cat);
	})
	$("#colorAdd").click(function(){
		$("#colors").append(`
			<div class="input-group mb-2" style="padding-right:10%;">
			<input class="form-control" type="text" name="rcolors"/>
			<button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">삭제</button>
			</div>
		`);
	});
	$("#sizeAdd").click(function(){
		$("#sizes").append(`
			<div class="input-group mb-2" style="padding-right:10%;">
			<input class="form-control" type="text" name="rsizes"/>
			<button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">삭제</button>
			</div>
		`);
	});
	$("#btnAdd").click(function(){
		$("#files").append(`
			<div class="input-group mb-2">
			<input class="form-control col-xs-3" type="file" name="ifile"/>
			<button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">취소</button>
			</div>
		`);
	});
	$("#btnAdd2").click(function(){
		$("#files2").append(`
			<div class="input-group mb-2">
			<input class="form-control col-xs-3" type="file" name="cfile"/>
			<button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">취소</button>
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
		const row = `<option value="${id}">${name}</option>`;
		out.push(row);
	});
	$("#tag").html(out.join("\n"));
}

function submitForm() {
	let existcolorCnt = $(".itemcolor").length;
	let existsizeCnt = $(".itemsize").length;
	let colorCnt = $("input[name='rcolors']").length;
	let cnt = colorCnt;
	for(let i = 0; i < colorCnt; i++) if ($("input[name=colors]").eq(i).val().trim() == "") cnt--;
	colorCnt = cnt;
	
	let sizeCnt = $("input[name='rsizes']").length;
	cnt = sizeCnt;
	for(let i = 0; i < sizeCnt; i++) if ($("input[name=sizes]").eq(i).val().trim() == "") cnt--;
	sizeCnt = cnt;
	
	if (colorCnt + existcolorCnt == 0) {alert("색상을 추가해주세요"); return false;}
	if (sizeCnt + existsizeCnt == 0) {alert("사이즈를 추가해주세요"); return false;}
}