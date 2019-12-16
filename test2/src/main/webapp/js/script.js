
$(document).ready(function() {

    // Очистка списка найденных ссылок
    $("#eraseButton").click(function() {
        $('tbody').empty();
    });

    // нажатие на кнопку "Анализировать"
    $("#linkButton").click(function() {
        sendAjax();
    });

    //Нажатие на Enter в поле ввода
    $('input').keydown(function(e) {
        if(e.keyCode === 13) {
            sendAjax();
            return false;
        }
      });
});

function sendAjax() {
    var linkData = {"linkInput":$("#linkInput").val()};
    // показываем WaitingBox
    $("#waitDiv").css("display", "block");
    $("#errorDiv").css("display", "none");
    //очищаем текущий список
    $('tbody').empty();
    //ajax запрос
    $.ajax
    ({
        type: 'POST',
        data: linkData,
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        success: function(result)
        {
            if (result.links.length === 0) {
                $("#errorDiv").css("display", "block").text("Ссылок не найдено!");
            } else {
            // заполнение таблицы найденными ссылками
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
                    // обработчик нажатия на ссылку
                    $a.on("click", function() {
                        var link = $(this).text();
                        $("#linkInput").val(link);
                        $('html, body').animate({scrollTop: 0}, 400);
                        return false;
                    });
                });
            }
            // прячем WaitingBox
            $("#waitDiv").css("display", "none");
            result = null;
        },
        // Если ошибка, выводим сообщение
        statusCode: {
            400: function(){
                $("#waitDiv").css("display", "none");
                $("#errorDiv").css("display", "block").text("Ошибка. Проверьте правильность написания ссылки");
            }
        },
        error: function(e)
        {
            $("#waitDiv").css("display", "none");
        }
    });
}