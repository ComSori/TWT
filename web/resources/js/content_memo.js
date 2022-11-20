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
    console.log(text);
    console.log(posX);
    console.log(posY);

    create_memo_data(text, posX, posY);

    var box = document.getElementById("memo_inputBox");
    box.style.display = "none";
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

    console.log(newMemo_cancel_button);
    board.append(newMemo);


    /* <div id="memo_inputBox">
    <div className="memo_controlBar">
        <button id="cancel_button" type="button" onClick="memo_cancel()">
            <svg className="memo_cancel" stroke="currentColor" fill="currentColor" stroke-width="0"
                 viewBox="0 0 16 16" height="25" width="25" xmlns="http://www.w3.org/2000/svg">
                <path
                    d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"></path>
            </svg>
        </button>
        <button id="add_button" type="button" onClick="memo_submit()">
            <svg className="memo_submit" stroke="currentColor" fill="currentColor" stroke-width="0"
                 viewBox="0 0 16 16" height="25" width="25" xmlns="http://www.w3.org/2000/svg">
                <path
                    d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"></path>
            </svg>
        </button>
    </div>
    <input type="text" placeholder="test" name="memo_content" id="memo_inputText"></input>
</div>*/
}