//动态绑定（科目树分类）（一，二，三，四）级分类
function BindTreeToDDL(target, parentID) {
    if ( null != parentID){
        $.ajax({
            url: basePath+"/common-api/tree_names?treeParentID=" + parentID,
            type: "post",
            dataType: "json",
            traditional: true,
            success: function (data) {
                var jsonData = data.body;
                if (jsonData != null && jsonData.length > 0) {
                    var optionstring = "";
                    for (var i = 0; i < jsonData.length; i++) {
                        optionstring += "<option value=\"" + jsonData[i].treeID + "\" >" + jsonData[i].treeName + "</option>";
                    }
                    $(target).append(optionstring);
                }
            },
            error: function () {
                //alert('请求失败，请稍后重试！');
            }
        });
    }
}
