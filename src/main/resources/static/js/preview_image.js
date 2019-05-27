/**
 *  preivew image
 */

$(function(){
	
	$("#profile_image").change(function(){
		
		var fileName = $(this).val().split('/').pop().split('\\').pop();

		if(!checkImageType(fileName)){
			alert("이미지 파일이 아닙니다.");
			$(this).val("");
		}
		else{
			imageCheck(this);
		}
	});
});

function imageCheck(obj){

	if(obj.files && obj.files[0]){

		var reader = new FileReader();
		
		reader.onload = function(e){
			$('#preview').attr('src', e.target.result);
		}
		reader.readAsDataURL(obj.files[0]);
	}
}

function checkImageType(fileName){
	
	var pattern = /jpg|gif|png|jpeg/i; // 정규표현식
	return fileName.match(pattern); // 규칙에 맞으면 true
}
