
$(function() {
	// 현재 글의 id값
	let id = $("input[name='itemId']").val();
	loadComment(id);
	//별점
	(function () {
    let starEls = document.querySelectorAll('#star span.star');
    loop(starEls, function (el, index) {
        el.addEventListener('click', function () {
            rating(index + 1);
        });
    });

    function loop(list, func) {
        Array.prototype.forEach.call(list, func);
    }

    function rating(score) {
        loop(starEls, function (el, index) {
            if (index < score) {
                el.classList.add('on');
            } else {
                el.classList.remove('on');
            }
        });

        star = score;
		
       }
})();
	// 댓글 작성 버튼 누르면 댓글 등록 하기.  
    // 1. 어느글에 대한 댓글인지? --> 위에 id 변수에 담겨있다
    // 2. 어느 사용자가 작성한 댓글인지? --> logged_uid 값
    // 3. 댓글 내용은 무엇인지?  --> 아래 content
    $("#btn_review").click(function(){
		// 입력한 값
        let content = $("#input_review").val();
        // 검증
        if(!content){
            alert("댓글 입력을 하세요");
            return;
        }	
		// 전달할 parameter 준비
		let data = {
			"itemId":id,
			"user_id":logged_id,
			"content":content,
			"star":star
		};
		
		$.ajax({
			url:"registerReview",
			type:"POST",
			data:data,
			cache:false,
			success : function(data, status, xhr){
                if(status == "success") {
                    //alert(xhr.responseText);  // JSON 데이터 확인용.
                    if(data.status !== "OK"){
                        alert(data.status) ;
                        return;
                    }
                    loadComment(id); // 댓글 목록 다시 업데이트
                    $("#input_review").val('');
                }
            }
		});

	});
})
// 특정 글(hotel_id)의 댓글 목록 읽어오기
function loadComment(item_id) {
	$.ajax({
		url: "reviewlist?id=" + item_id,
		type: "GET",
		cache: false,
		success: function(data, status) {
			if (status == "success") {
				// 서버측 에러 메시지 있는 경우
				if (data.status != "OK") {
					alert(data.status);
					return;
				}
				buildComment(data); // 댓글 목록 렌더링
				addDelete(); //댓글 목록 불러온 뒤 삭제에 대한 이벤트리스너 등록
			}
		},
	});	
}

function buildComment(result) {
	$("#cmt_cnt").text(result.count);
	let sum = 0 ;
	for(i=0; i < result.count; i++){
		sum += result.data[i].star;
	}
	const sums = (sum/result.count).toFixed(1);
	$('#star_cnt').text(sums);
	
	const out = [];
	
	result.data.forEach(comment => {
		let id = comment.id;
        let content = comment.content;
        let star = comment.star;
        let regdate = comment.regdate;
        let user_id = parseInt(comment.user.id);
        let username = comment.user.username;
        let name = comment.user.name;
      
        // 삭제버튼 여부 : 작성자 본인의 댓글인 경우에만 보이기
        let delBtn = (logged_id !== user_id) ? '' : `
        	<i class="btn fa-solid fa-delete-left text-danger" data-bs-toggle="tooltip" data-cmtdel-id="${id}" title="삭제"></i>`;
		let row = `
	        <tr>
	        <td>
	        	<span>
	        		<strong>
	        			${username}
	        		</strong>
	        		<br>
	        		<small class="text-secondary">
	        			(${name})
	        		</small>
	        	</span>
	        </td>
	        <td>
	            <span>
	          		  ${content}
	            </span>
	           		 ${delBtn}            
	        </td>
	        <td>
	        	<span id="starrr">
	        		★
	        	</span>
	        	<span>
	        	${star}
	        	</span>
	        </td>
	        	<td>
	        		<span>
	        			<small class="text-secondary">
	        				${regdate}
	        			</small>
	        		</span>
	        	</td>
	        </tr>`;
		out.push(row);
	});
	$("#cmt_list").html(out.join("\n"));
}

// 댓글삭제버튼이 눌렸을때 해당 댓글 삭제하는 이벤트리스너를 삭제버튼에 등록
function addDelete() {
	// 현재 글의 id
	const id = $("input[name='id']").val();
	
	$("[data-cmtdel-id]").click(function() {
		if (!confirm("댓글을 삭제하시겠습니까?")) return;
		
		// 삭제할 댓글의 id
		const review_id = $(this).attr("data-cmtdel-id");
		$.ajax({
			url: "deleteReview",
			type: "POST",
			cache: false,
			data: {"id": review_id},
			success: function(data, status) {
				if (status == "success") {
					if (data.status !== "OK") {
						alert(data.status);
						return;
					}
					
					// 삭제후에는 다시 목록 불러오기
					loadComment(id);
				}
			},
		});
	})
}
