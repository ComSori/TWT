function clickSpace(event) {
    if(getComputedStyle(document.getElementById("memo_inputBox")).display == "none"){
        var x = event.offsetX;
        var y = event.offsetY;
        console.log(x + " " + y);
        var box = document.getElementById("memo_inputBox");
        box.style.display = "block";
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

}