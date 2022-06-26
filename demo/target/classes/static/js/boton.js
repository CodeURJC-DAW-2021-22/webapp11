
var currentPage = 0;
function moreContentAdmin() {

    switchMoreContentButtonActivation(true)

    currentPage = currentPage + 1;

    $.ajax('https://localhost:8443/api/profile/games?numPage=' + currentPage,{

        error: function(){

            console.log('Something went wrong')

            switchMoreContentButtonActivation(false)

        },

        success: function(data){

            console.log(data)

            if (data != undefined && data.content != undefined && data.content.length > 0){

                for(let game of data.content){
                    console.log("fu")
                    addGame(game)

                }

                switchMoreContentButtonActivation(false)

            } else {

                $('#moreContentButton').css("display", "none");

            }

        }

    })

};

function addGame(game){

    $('#userGames').append(

        '<p><a href="/games/' + game.id + '">'+

           + game.title +

        '</a></p>')

}



function switchMoreContentButtonActivation(disabled){

    $('#moreContentButton').attr('disabled', disabled);

    if (disabled){

        $('#moreContentSpinner').css("display", "block");

        $('#moreContentText').css("display", "none");

    } else {

        $('#moreContentSpinner').css("display", "none");

        $('#moreContentText').css("display", "block");

    }

}