
function clickSpace(event) {
    if(getComputedStyle(document.getElementById("memo_inputBox")).display == "none"){
        var x = event.offsetX;
        var y = event.offsetY;
        console.log(event.target.getAttribute("id"));

        if(event.target.getAttribute("id") == "memo_controlBar") {

            x = parseInt(x) + parseInt(event.target.parentElement.style.left.replace("px", ""));
            y = parseInt(y) + parseInt(event.target.parentElement.style.top.replace("px", ""));
        } else if(event.target.getAttribute("id") == "memo_inputText") {
            x = parseInt(x) + parseInt(event.target.parentElement.style.left.replace("px", ""));
            y = parseInt(y) + parseInt(event.target.parentElement.style.top.replace("px", "")) +25;
        }
        var box = document.getElementById("memo_inputBox");
        box.style.display = "inline-block";
        box.style.left = x + "px";
        box.style.top = y + "px";
        event.stopPropagation();
    }
}

function memo_cancel() {
    console.log("cancel");

    var box = document.getElementById("memo_inputBox");
    box.style.display = "none";
    event.stopPropagation();
}

function memo_submit() {
    console.log("submit");
    /******************  TODO - DB에 입력
     *  속성 가져오기   *
     *****************/
    var text = document.getElementById("memo_inputText").value;
    var posX = document.getElementById("memo_inputBox").style.left;
    var posY = document.getElementById("memo_inputBox").style.top;

    if(text != null) {
        create_memo_data(text, posX, posY);

        var box = document.getElementById("memo_inputBox");
        box.style.display = "none";
        document.getElementById("memo_inputText").value = ""; // text reset
    }
    else {
        alert("텍스트를 입력해주세요");
        window.history.back();
    }

    event.stopPropagation();

}

function create_memo_data(text, posX, posY) {
    /******************   TODO - 반복문으로 DB에서 값 불러와서 출력
     * 새로운 박스 생성  *
     ******************/

    var board = document.getElementById("memo_space");
    var newMemo = document.createElement("div");
    newMemo.setAttribute("id", "memo_inputBox_updated");
    newMemo.style.top = posY;
    newMemo.style.left = posX;
        var newMemo_controlBox = document.createElement("div");
        newMemo_controlBox.setAttribute("id", "memo_controlBar");
            var newMemo_cancel_button = document.createElement("button");
            newMemo_cancel_button.setAttribute("id", "cancel_button");
            newMemo_cancel_button.setAttribute("type", "button");
            newMemo_cancel_button.onclick = function(){
                newMemo.remove();
                event.stopPropagation();
            };
        newMemo_controlBox.append(newMemo_cancel_button);

        var newMemo_textBox = document.createElement("div");
        newMemo_textBox.setAttribute("id", "memo_inputText");
        newMemo_textBox.innerText += text;


    newMemo.append(newMemo_controlBox);
    newMemo.append(newMemo_textBox);

    console.log(newMemo);
    board.append(newMemo);
}
