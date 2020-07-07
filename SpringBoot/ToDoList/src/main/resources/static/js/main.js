$(function(){

    const appendTask = function(data){
            var taskCode = '<a href="#" class="task-link" data-id="' +
                data.id + '">' + data.name + '</a><br>';
            $('#task-list')
                .append('<div>' + taskCode + '</div>');
    };

    //Show adding task form
    $('#show-add-task-form').click(function(){
        $('#task-form').css('display', 'flex');
    });

    //Show edit task form
    $(document).on('click', '.task-edit', function(){
        $('#edit-task-form').css('display', 'flex');
        var link = $(this);
        var taskId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/tasks/' + taskId,
            success: function(response)
            {
                $('#id').val(response.id);
                $('#name').val(response.name);
                $('#done').val(response.done);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Задача не найдена!');
                }
            }
        });
        return false;
    });

    //Closing adding task form
    $('#task-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Closing edit task form
    $('#edit-task-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting task
    $(document).on('click', '.task-link', function(){
        var link = $(this);
        var taskId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/tasks/' + taskId,
            success: function(response)
            {
                var code = '<span>Выполнено: ' + response.done + '</span>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Задача не найдена!');
                }
            }
        });
        return false;
    });

    //Task done
    $(document).on('click', '.task-done', function(){
            var link = $(this);
            var taskId = link.data('id');
            $.ajax({
                method: "PATCH",
                url: '/tasks/' + taskId,
                success: function(response)
                {
                    window.location.reload();
                },
                error: function(response)
                {
                    if(response.status == 404) {
                        alert('Задача не найдена!');
                    }
                }
            });
            return false;
        });

    //Task delete
    $(document).on('click', '.task-delete', function(){
            var link = $(this);
            var taskId = link.data('id');
            $.ajax({
                method: "DELETE",
                url: '/tasks/' + taskId,
                success: function(response)
                {
                    window.location.reload();
                },
                error: function(response)
                {
                    if(response.status == 404) {
                        alert('Задача не найдена!');
                    }
                }
            });
            return false;
        });

    //Edit task
    $("#edit-task-form").submit(function(e) {

        e.preventDefault();

        var form = $(this);
        var taskId = $('input#id').val();

        $.ajax({
               type: "POST",
               url: '/tasks/' + taskId,
               data: $('#edit-task-form form').serialize(),
               success: function(response)
               {
                    $('#edit-task-form').css('display', 'none');
                    window.location.reload();
                    var task = response.task;
                    var dataArray = $('#edit-task-form form').serializeArray();
                    for(i in dataArray) {
                        task[dataArray[i]['name']] = dataArray[i]['value'];
                    }
               }
               error: function(response)
               {
                   if(response.status == 404) {
                       alert('Задача не найдена!');
                   }
               }
             });
    });

    //Adding task
    $('#save-task').click(function()
    {
        var data = $('#task-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/tasks/',
            data: data,
            success: function(response)
            {
                $('#task-form').css('display', 'none');
                var task = {};
                task.id = response.id;
                var dataArray = $('#task-form form').serializeArray();
                for(i in dataArray) {
                    task[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendTask(task);
                window.location.reload();
            }
        });
        return false;
    });
});