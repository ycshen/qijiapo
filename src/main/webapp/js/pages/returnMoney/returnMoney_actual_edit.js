

function isNotBlank(args) {
    var result = false;
    if (args != "" && args != null && args != undefined) {
        result = true;
    }

    return result;
}

function cancelEdit(){
	parent.layer.closeAll();
}