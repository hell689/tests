
$(document).ready(function() {

    $("#eraseButton").click(function() {
        $('tbody').empty();
    });

    $("#linkButton").click(function() {
        var linkData = {"linkInput":$("#linkInput").val()};
        $("#waitDiv").css("display", "block");
        $('tbody').empty();
        $.ajax
        ({
            type: 'POST',
            data: linkData,
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            success: function(result)
            {
                var i = 1, $tr, $td, $a;
                result.links.forEach(function(value) {
                    $tr = $('<tr>').appendTo($('tbody'));
                    $td = $('<td>').appendTo($tr);
                    $td.text(i++);
                    $td = $('<td>').appendTo($tr);
                    $td.text(value.name);
                    $td = $('<td>').appendTo($tr);
                    $a = $('<a>').appendTo($td);
                    $a.attr("href", value.link).addClass("webLink").text(value.link);
                    $a.on("click", function() {
                        var link = $(this).text();
                        $("#linkInput").val(link);
                        return false;
                    });
                });
                $("#waitDiv").css("display", "none");
                result = null;
            },
            statusCode: {
                400: function(result, result){
                    $("#waitDiv").css("display", "none");
                    alert("Ошибка. Проверте правильность написания ссылки");
                }
            },
            error: function(e)
            {
                $("#waitDiv").css("display", "none");
            }
        });
    });

});