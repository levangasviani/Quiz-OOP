var points = 0;
var num = 0;
var questionTypes = document.getElementsByName("questionType");
var length = questionTypes.length;
var questionIds = document.getElementsByName("questionId");
var times = document.getElementsByName("time");
var checkedNumber = 0;

var startTime;
var minutes;
var seconds;
function display() {
	var endTime = new Date();
	var timeDiff = endTime - startTime;
	timeDiff /= 1000;
	seconds = Math.round(timeDiff % 60);
	timeDiff = Math.floor(timeDiff / 60);
	minutes = Math.round(timeDiff % 60);
	$("#elapsedtime").text("Elapsed Time: " + minutes + ":" + seconds);
	setTimeout(display, 1000);
}

function elapsedTime() {
	startTime = new Date();
	setTimeout(display, 1000);
}

function OnePageProcess() {
	elapsedTime();
	OnePageStart();
}

function OnePageStart() {
	document.getElementById("butt").style.display = "none";
	var questionI = questionIds[num].value;
	var questionTyp = questionTypes[num].value;
	var file = getFile(questionTyp);
	$
			.post(
					file + ".jsp",
					{
						questionId : questionI,
						questionType : questionTyp
					},
					function(data, status) {
						document.getElementById("questions").innerHTML += "<div id='"
								+ questionI + "'>" + data + "</div>";
						num++;
						if (num <= length - 1)
							OnePageStart();
						else {
							document.getElementsByTagName("body")[0].innerHTML += "<input type='Submit' id = 'subID' value='submit' onclick='finishQuiz()'>"
						}
					});
}

function getFile(questionTyp) {
	if (questionTyp == 1) {
		return "ShowQuestionResponse";
	} else if (questionTyp == 2) {
		return "ShowFillInTheBlank";
	} else if (questionTyp == 3) {
		return "ShowMultipleChoice";
	} else if (questionTyp == 4) {
		return "ShowPictureResponse";
	} else if (questionTyp == 5) {
		return "ShowMultipleAnswers";
	} else if (questionTyp == 6) {
		return "ShowMultipleChoiceMultipleAnswers";
	} else if (questionTyp == 7) {
		return "ShowMatching";
	} else {
		return "ShowGradedQuestion";
	}
	return "";
}

var checknum = 0;
function finishQuiz() {

	var i;
	for (i = 0; i < length; i++) {
		var questionType = questionTypes[i].value;
		var questionId = questionIds[i].value;
		checkOneAns(questionType, questionId);
	}

}

function redirectToStats() {
	var elapsedtime = minutes * 60 + seconds;
	document.getElementById("score").value = points;
	document.getElementById("elapsedTime").value = elapsedtime;
	checkedNumber = 0;
	document.getElementById("tostat").submit();
}

function question_response(questionType, questionId) {
	var elements = document.getElementById(questionId).getElementsByTagName(
			"input");
	$.post("QuestionCheck", {
		answer : elements[0].value,
		type : questionType,
		Id : questionId
	}, function(data) {
		points += parseInt(data);
		updatePoints();
		checkedNumber++;

		if (checkedNumber == length) {
			redirectToStats();
		}
	});
}

function updatePoints() {
	document.getElementById("points").innerHTML = points;
}

function fill_blank(questionType, questionId) {
	var elements = document.getElementById(questionId).getElementsByTagName(
			"input");
	$.post("QuestionCheck", {
		answer : elements[0].value,
		type : questionType,
		Id : questionId
	}, function(data) {
		points += parseInt(data);
		updatePoints();
		checkedNumber++;

		if (checkedNumber == length) {
			redirectToStats();
		}
	});
}

function multiple_choice(questionType, questionId) {
	var elements = document.getElementById(questionId).getElementsByTagName(
			"input");
	var j;
	for (j = 0; j < 4; j++) {
		if (elements[j].checked) {
			$.post("QuestionCheck", {
				answer : elements[j].value,
				type : questionType,
				Id : questionId
			}, function(data) {
				points += parseInt(data);
				updatePoints();
				checkedNumber++;
				if (checkedNumber == length) {
					redirectToStats();
				}

			});
			return;
		}
	}
	checkedNumber++;
	if (checkedNumber == length) {
		redirectToStats();
	}

}

function picture_response(questionType, questionId) {
	var elements = document.getElementById(questionId).getElementsByTagName(
			"input");
	$.post("QuestionCheck", {
		answer : elements[0].value,
		type : questionType,
		Id : questionId
	}, function(data) {
		points += parseInt(data);
		updatePoints();
		checkedNumber++;

		if (checkedNumber == length) {
			redirectToStats();
		}
	});
}

function multiple_answer(questionType, questionId) {
	var elements = document.getElementById(questionId).getElementsByTagName(
			"input");
	var inputAnswers = elements[0].value;
	var j;
	for (j = 1; j < elements.length; j++) {
		inputAnswers += ":" + elements[j].value;
	}

	$.post("QuestionCheck", {
		answer : inputAnswers,
		type : questionType,
		Id : questionId
	}, function(data) {
		points += parseInt(data);
		updatePoints();
		checkedNumber++;
		if (checkedNumber == length) {
			redirectToStats();
		}
	});
}

function multiple_choice_multiple_answer(questionType, questionId) {
	var elements = document.getElementById(questionId).getElementsByTagName(
			"input");
	var j;
	var inputAnswers = "";
	var numm = 0;
	for (j = 0; j < 4; j++) {
		if (elements[j].checked) {
			if (numm == 0) {
				inputAnswers = elements[j].value;
				numm++;
			} else {
				inputAnswers += ":" + elements[j].value;
			}
		}
	}
	$.post("QuestionCheck", {
		answer : inputAnswers,
		type : questionType,
		Id : questionId
	}, function(data) {
		points += parseInt(data);
		checkedNumber++;
		updatePoints();

		if (checkedNumber == length) {
			redirectToStats();
		}
	});
}

function matching(questionType, questionId) {
	var left = document.getElementById(questionId).getElementsByTagName("a");
	var chosen = document.getElementById(questionId).getElementsByTagName(
			"select");
	var j;
	inputAnswers = left[0].innerHTML + ":"
			+ chosen[0][chosen[0].selectedIndex].value;
	for (j = 1; j < left.length; j++) {
		inputAnswers += ":" + left[j].innerHTML + ":"
				+ chosen[j][chosen[j].selectedIndex].value;
	}
	$.post("QuestionCheck", {
		answer : inputAnswers,
		type : questionType,
		Id : questionId
	}, function(data) {
		points += parseInt(data);
		updatePoints();
		checkedNumber++;

		if (checkedNumber == length) {
			redirectToStats();
		}
	});
}

function graded(questionType, questionId) {
	var sender = document.getElementById("sender").value;
	var receiver = document.getElementById("receiver").value;
	var elements = document.getElementById(questionId).getElementsByTagName(
			"input");
	$.post("NotificationServlet", {
		sender : sender,
		receiver : receiver,
		type : 3,
		message : "SENT:" + questionId + ":" + elements[0].value
	}, function(data) {

	});
	checkedNumber++;
	if (checkedNumber == length) {
		redirectToStats();
	}
}

function MultiPageProcess() {
	elapsedTime();
	MultiPageStart();
}

function MultiPageStart() {
	if (num == length) {
		return;
	}
	var i;
	document.getElementById("butt").style.display = "none";
	var questionTyp = questionTypes[num].value;
	var questionI = questionIds[num].value;
	var time = times[num].value;
	num++;

	var file = getFile(questionTyp);
	$
			.post(
					file + ".jsp",
					{
						questionId : questionI,
						questionType : questionTyp
					},
					function(data, status) {
						document.getElementById("questions").innerHTML = "<div id='"
								+ questionI + "'>" + data + "</div>";
						document.getElementById("subm").innerHTML = "<input type='submit' id='subID' value='submit' onclick='getNextQuestion("
								+ questionTyp + ", " + questionI + ")'>";
						if (time != -1) {
							setCountDown(time);
						} else {
							document.getElementById("timeLeft").innerHTML = "";
						}
					});

}

function getNextQuestion(questionType, questionId) {
	checkOneAns(questionType, questionId);
	clearTimeout(disp);
	MultiPageStart();
}

function checkOneAns(questionType, questionId) {
	if (questionType == 1) {
		question_response(questionType, questionId);
	} else if (questionType == 2) {
		fill_blank(questionType, questionId);
	} else if (questionType == 3) {
		multiple_choice(questionType, questionId)
	} else if (questionType == 4) {
		picture_response(questionType, questionId);
	} else if (questionType == 5) {
		multiple_answer(questionType, questionId);
	} else if (questionType == 6) {
		multiple_choice_multiple_answer(questionType, questionId);
	} else if (questionType == 7) {
		matching(questionType, questionId);
	} else {
		graded(questionType, questionId);
	}
}

var countDownTime;

function setCountDown(time) {
	countDownTime = time;
	countDownDate = new Date();
	displayTime();
}

var disp;

function displayTime() {
	var min = Math.floor(countDownTime / 60);
	var sec = Math.floor(countDownTime - min * 60);
	if (countDownTime < 0) {
		document.getElementById("nextq").click();
		return;
	}
	countDownTime--;
	document.getElementById("timeLeft").innerHTML = min + "m " + sec + "s";
	disp = setTimeout(displayTime, 1000);
}
