function fill_blank_checkAnswer() {
	var questionText = document.getElementsByName("questionText");
	var text = questionText[0].value;
	if (findBlank(text)) {
		return true;
	} else {
		alert("illegal formatting, try again");
		return false;
	}
}

function findBlank(text) {
	var i;
	var numOfBlanks = 0;
	for (i = 0; i < text.length; i++) {
		var num = 0;
		var j;
		for (j = i; j < text.length; j++) {
			if (text[j] == '_') {
				num++;
			} else {
				break;
			}
		}
		if (num == 7) {
			numOfBlanks++;
		}
		i = j;
	}
	return numOfBlanks == 1;
}

function fill_blank_submit() {
	if (!fill_blank_checkAnswer())
		return false;
	answers = document.getElementsByName("possible-answers");
	var possible = {};
	var t = answers[0].value;
	possible[t] = "TRUE";
	addObject(possible);
}

function graded_submit() {
	var possible = {};
	possible[""] = "FALSE";
	addObject(possible);
}

function matching_submit() {
	answers = document.getElementsByName("possible-answers");
	var possible = {};
	var i;
	for (i = 0; i < answers.length; i += 2) {
		var t = answers[i].value + ":" + answers[i + 1].value;
		possible[t] = "TRUE";
	}
	addObject(possible);
}

function addPair() {
	answers = document.getElementById("answers");
	answers.innerHTML += '<br><input type="text" name="possible-answers"> <input type="text" name="possible-answers"><br>';
}

function multi_answer_submit() {
	answers = document.getElementsByName("possible-answers");
	var possible = {};
	var i;
	var str = answers[0].value;
	for (i = 1; i < answers.length; i++) {
		str += ":" + answers[i].value;
	}
	possible[str] = "TRUE";
	if (document.getElementById("ordered").checked) {
		document.getElementsByName("answerOrder")[0].value = "TRUE";
	}
	addObject(possible);
}

function addField() {
	document.getElementById("answers").innerHTML += '<br><input type="text" class="possible-answers" name="possible-answers"><br>';
	return false;
}

function illegal() {
	alert("illegal formatting, try again");
}

function multiple_choice_multiple_answer_submit() {
	answers = document.getElementsByName("possible-answers");
	answersCorrectness = document.getElementsByName("options");
	var possible = {};
	var i;
	for (i = 0; i < answers.length; i++) {
		var t = answers[i].value;
		possible[t] = answersCorrectness[i].value;
	}
	addObject(possible);
}

function multiple_choice_checkAnswers() {
	var possibleAnswers = document.getElementsByClassName("possible-answers");
	var number = document.getElementById("answers").value;
	var num = parseInt(number, 10);
	if (num < 1 || num > 4) {
		alert("Enter correct Number");
		return false;
	}
	return true;
}

function multiple_choice_submit() {
	if (!multiple_choice_checkAnswers())
		return false;
	answers = document.getElementsByName("possible-answers");
	var possible = {};
	var correct = document.getElementById("answers").value;
	var i;
	for (i = 0; i < answers.length; i++) {
		var t = answers[i].value;
		if (i == correct - 1) {
			possible[t] = "TRUE";
		} else {
			possible[t] = "FALSE";
		}
	}
	addObject(possible);
}

function picture_response_submit() {
	answers = document.getElementsByName("possible-answers");
	var possible = {};
	var t = answers[0].value;
	possible[t] = "TRUE";
	addObject(possible);
}

function question_response_submit() {
	answers = document.getElementsByName("possible-answers");
	var possible = {};
	var t = answers[0].value;
	possible[t] = "TRUE";
	addObject(possible);
}

function addObject(possible) {
	var texts = document.getElementsByName("questionText");
	var txt = texts[0].value;
	var i;
	for (i = 1; i < texts.length; i++) {
		txt += ":" + texts[i].value;
	}
	var object = {
		type : document.getElementsByName("type")[0].value,
		questionText : txt,
		answer : JSON.stringify(possible),
		answerOrder : document.getElementsByName("answerOrder")[0].value,
		checkType : document.getElementsByName("checkType")[0].value,
		time : document.getElementsByName("time")[0].value,
	};
	var str = JSON.stringify(object);
	var form = document.forms["questions"];
	var input = document.createElement('input');
	input.name = "question";
	input.type = "hidden";
	input.value = str;
	form.appendChild(input);
	updateSummary();
}