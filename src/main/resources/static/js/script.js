$("#addQuestionBTN").on('click', function() {
	var answerList = document.querySelectorAll(".answerInput");
	var checkboxes = document.querySelectorAll("input[type='checkbox']");
	var answers = [];
	
	for(i=0;i<answerList.length;i++) {
		var isCorrect = $(checkboxes[i]).prop("checked");
		var answerText = $(answerList[i]).val();
		
		var answer = {answer: answerText, 
					  correct: isCorrect};

		answers.push(answer);
	}
	
	var answersJSON = JSON.stringify(answers);
	
	var question = $("#questionInput").val();
	var quizId = $("#quizHeader").attr("data-quizid");
	
	$.ajax({
		 type: "POST",
		 url: "/submitQuestion",
		 data: {quizId: quizId,
			    question: question,
			    answers: answersJSON},
	     dataType: "json",
		 complete: function(result) {
			 window.location = "/quizzes/" + quizId + "/questions";
		 }
	});
});

$("#updateQuestionBTN").on('click', function() {
	var quizId = $("#quizHeader").attr("data-quizid");
	var questionId = $("#quizHeader").attr("data-questionid");
	var question = $("#questionInput").val();
	
	$.ajax({
		 type: "PUT",
		 url: "/updateQuestion",
		 data: {questionId: questionId,
			    question: question},
	     dataType: "json",
		 complete: function(result) {
			 window.location = "/quizzes/" + quizId + "/questions";
		 }
	});
});

$("#addAnswerBTN").on('click', function() {
	var quizId = $("#quizHeader").attr("data-quizid");
	var questionId = $("#quizHeader").attr("data-questionid");
	
	var answer = $("#answerInput").val();
	var correct = $("#correctInput").val();
	
	$.ajax({
		 type: "POST",
		 url: "/submitAnswer",
		 data: {questionId: questionId,
			    answer: answer,
			    correct: correct},
	     dataType: "json",
		 complete: function(result) {
			 window.location = "/quizzes/" + quizId + "/questions/answer/view?questionId=" + questionId;
		 }
	});
});

$("#updateAnswerBTN").on('click', function() {
	var quizId = $("#quizHeader").attr("data-quizid");
	var questionId = $("#quizHeader").attr("data-questionid");
	var answerId = $("#quizHeader").attr("data-answerid");
	var answer = $("#answerInput").val();
	
	$.ajax({
		 type: "PUT",
		 url: "/updateAnswer",
		 data: {answerId: answerId,
			    answer: answer},
	     dataType: "json",
		 complete: function(result) {
			 window.location = "/quizzes/" + quizId + "/questions/answer/view?questionId=" + questionId;
		 }
	});
});

$("#totalAnswersSelection").change(function() {
	var selectedTotal = $(this).children("option:selected").val();
	var tbody = document.querySelector("#addAnswersTable > tbody");
	
	$(".answerRow").remove();
	
	for(i=0;i<selectedTotal;i++) {
		var tableRow = 	"<td><input class='answerInput' type='text' name='answer" + (i+1) +"' required /></td><td><input type='checkbox' name='correct" + (i+1) +"' value='" + (i+1) + "'></td>"
		var htmlTableRow = document.createElement("tr");
		htmlTableRow.classList.add("answerRow");
		htmlTableRow.innerHTML = tableRow;
		
		tbody.appendChild(htmlTableRow);
		
		$(":checkbox").change(function() {		
			var checkboxes = document.querySelectorAll("input[type='checkbox']");

			for (i = 0; i < checkboxes.length; ++i) {
				$(checkboxes[i]).prop("checked", false);
			}
			
			$(this).prop("checked", true);
		});
	}
	
	$("input[value=1]").prop("checked", true);
});

document.querySelectorAll("#quizRemoveBtn").forEach(removeButton => {
	$(removeButton).on("click", function() {
		
		var row = this.parentElement.parentElement;
		var quizId = $(row).attr("data-quizId");
		
		console.log(quizId);
		
		$.ajax({
			 type: "DELETE",
			 url: "/quizzes/removeQuiz",
			 data: {quizId: quizId},
		     dataType: "json",
			 complete: function(result) {
				 location.reload();		 
			 }
		});
	});
});

document.querySelectorAll("#questionRemoveBtn").forEach(removeButton => {
	$(removeButton).on("click", function() {

		var row = this.parentElement.parentElement;
		var questionId = $(row).attr("data-questionId");
		
		$.ajax({
			 type: "DELETE",
			 url: "/removeQuestion",
			 data: {questionId: questionId},
		     dataType: "json",
			 complete: function(result) {
				 location.reload();		 
			 }
		});
	});
});

document.querySelectorAll("#answerRemoveBtn").forEach(removeButton => {
	
	var row = removeButton.parentElement.parentElement;
	var answerIsCorrect = $(row).attr("data-correct");
	
	if(answerIsCorrect == "true") {
		removeButton.style.display = "none";
	}
	else {
		$(removeButton).on("click", function() {
	
			var row = this.parentElement.parentElement;
			var answerId = $(row).attr("data-answerId");
			
			$.ajax({
				 type: "DELETE",
				 url: "/removeAnswer",
				 data: {answerId: answerId},
			     dataType: "json",
				 complete: function(result) {
					 location.reload();		 
				 }
			});
		});
	}
});

$(document).ready(function() {
	var letters = ['A', 'B', 'C', 'D', 'E'];
	var index = 0;
    document.querySelectorAll("#answerOrderCell").forEach(orderCell => {
    	orderCell.innerHTML = letters[index];
    	index++;
    });
    
    index = 1;
    
    document.querySelectorAll("#questionOrderCell").forEach(orderCell => {
    	orderCell.innerHTML = index;
    	index++;
    });
    
    var totalAnswersSelect = document.getElementById("totalAnswersSelection");
    if(null != totalAnswersSelect) {
    	$(totalAnswersSelect).prop('selectedIndex', 1);
    	totalAnswersSelect.dispatchEvent(new Event('change'));
    }
});

function logout() {
	$.ajax({
	  	url: "/logout",
	  	method: "POST"
	}).done(function() {
		console.log("Successfully Logged Out");
	});
}
