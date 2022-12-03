
function create_button(u_id) {
    alert("test");
    var board = document.getElementById("sideBar");
    var newButton = document.createElement("button");
    newButton.setAttribute("id", "u_id");
    newButton.setAttribute("value", "u_id");
    newButton.setAttribute("type", "button");
    // newMemo.style.top = posY;
    // newMemo.style.left = posX;
    board.append(newButton);
}
