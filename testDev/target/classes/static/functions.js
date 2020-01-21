//const ? 
const gameSetting = {
		numPlayer: null,
		grid: null
};

let gridArray;
let playerArray = ["O","X","&#9634;","&#9671;"]
let playerTurn = 0;

  function initModal(){
	  let dialog = $("#dialog");
	    dialog.dialog({
	        autoOpen: false,
	        height: 650,
	        width: 625,
	        modal: true
	    });
  }
  
  function startGame(){
	  gameSetting.numPlayer = $("input[name='playerNum']:checked").val();
	  gameSetting.grid = $("input[name='grid']:checked").val();
	  
	  if(gameSetting.grid === undefined || gameSetting.numPlayer === undefined){
		  console.log("select something")
		  $("#alertRow").show();
	  }else{
		  $( "#dialog" ).dialog("close");
	  }
	  
	  //call to RESTcontroller
	  $.ajax({
		  type: "GET",
		  url: "/startGame",
		  data: "numPlayer=" +  gameSetting.numPlayer + "&grid=" + gameSetting.grid,
		  dataType: "html",
		  success: function(risposta){
		    history.pushState(null, '', '/games?ID='+risposta);  
		  },
		  error: function(){
		    alert("Chiamata fallita!!!");
		  }
		});
	  
	  createTable();
	  //il substring in backend funziona male (lengh=7 se assente prima e ultimo valore) con ,,,,,,,
	  //gridArray = Array.apply(null, new Array(gameSetting.grid*gameSetting.grid))
	  gridArray = Array.from({ length: gameSetting.grid*gameSetting.grid }, (_, idx) => `_`)
  }
  
  function loadGame(){
	  
	gameSetting.grid= $("#placeholder").text().substring(0,1);
	gameSetting.numPlayer= $("#placeholder").text().substring(4,5);
	playerTurn= $("#placeholder").text().substring(6,7);
	
	gridArray = Array.from({ length: gameSetting.grid*gameSetting.grid }, (_, idx) => 
	$("#placeholder").text().substring(8).replace(/,/g, '').charAt(idx));
	
	createTable();
	fillCells();
  }
  
  function fillCells(){
	  
	let tdElements = document.getElementsByTagName('td');
	for (let i = 0; i < tdElements.length; i++) { 
		//cella riempita da giocatore
		if(gridArray[i] !== '_'){
			  let unicodeText = document.createTextNode(playerArray[gridArray[i]]);
			  tdElements[i].innerHTML = unicodeText.data;
		}
	}
	//nextPlayer();
  }
  
  function createTable(){
	  let table = document.createElement('table');
	  let cellId= 0;
	  for (var i = 0; i < gameSetting.grid; i++){
		  let tr = document.createElement('tr');   
	      
	      for (var x = 0; x < gameSetting.grid; x++){
	    	  let td = document.createElement('td');
	    	  td.id = cellId;
	    	  td.addEventListener('click',cellClicked,false);
	    	  tr.appendChild(td);
	    	  cellId++;
	      }

	      table.appendChild(tr);
	     
	  }
	  document.body.appendChild(table);
  }
  
  //Event che avvia partita e relativi controlli, eliminando l'event posso fermare il gioco
  function cellClicked(cell){
	  
	  if(document.getElementById(cell.target.id).innerHTML == ""){
		  let unicodeText = document.createTextNode(playerArray[playerTurn]);
		  document.getElementById(cell.target.id).innerHTML = unicodeText.data;
		  
		  gridArray[cell.target.id] = playerTurn;
		  console.log("values:" + gridArray.toString());
		  nextPlayer();
		  checkWin(); 
		  checkDraw();
	  }
	  
  }
  
  function checkDraw(){
	  	
	  if(gridArray.every((value) => value !=="_")){
	    	document.querySelector(".displayWin").style.display = "block";
	    	document.querySelector(".displayWin .text").innerText = "Draw!";
	    	removeTdEvents();
	  }
  }
  
  //TODO: meh, gestione migliore? cappare valore senza usare una funzione?
  function nextPlayer(){
	  playerTurn++;
	  if(playerTurn == gameSetting.numPlayer){
		  playerTurn = 0;
	  }
  }
  
  function checkWin(){
	  
	  $.ajax({
		  type: "GET",
		  url: "/checkWin",
		  data: "gameID=" +  window.location.href.match(/(ID=\d+)/)[0].substring(3) + "&gridArray=" +  gridArray.toString() + 
		  "&playerTurn=" + playerTurn,
		  dataType: "html",
		  success: function(risposta){
		    console.log("rest: " + risposta)
		    if(risposta !== ''){
		    	document.querySelector(".displayWin").style.display = "block";
		    	var test= parseInt(risposta)+1;
		    	document.querySelector(".displayWin .text").innerText = "Player " + test + " Won!";
		    	
		    	removeTdEvents();
		    }
		  },
		  error: function(){
		    alert("Chiamata fallita!!!");
		  }
		});
  }
  
  function removeTdEvents(){
  	//getElementsByTagName non migliore strada per recuperare elementi html, 
  	//ma ho solo i td che mi interessano in pagina, no equivoci
  	let tdElements = document.getElementsByTagName('td');
  	for (let i = 0; i < tdElements.length; i++) { 
  		tdElements[i].removeEventListener('click',cellClicked); 
  		}
  }

/*
  $( document ).ready(function() {
	    console.log( "ready!" );
	    initModal();
	    $( "#dialog" ).dialog("open");
	    
	});
  */
