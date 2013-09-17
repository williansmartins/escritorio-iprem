<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>jQuery UI Dialog - Basic modal</title>
	<link rel="stylesheet"	href="css/estilos.css" />
	<link rel="stylesheet"	href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
	<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
	<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
	<script src="js/scripts.js"></script>
	<script>
		$(document).ready(function () {
			$( "#dialog-modal" ).hide();
		    $( "#create-user" )
		    .button()
		    .click(function() {
		        $( "#dialog-modal" ).dialog({
		            width: 800,
		            modal: true
		        });
		    });

		    $( "#chamada-ajax" )
		    .button()
		    .click(function() {
		    	chamarAjax( "externo.php" );
		    	$( "#dialog-modal" ).dialog({
		            width: 800,
		            position: ["center",20],
		            modal: true
		        });
		    });
		    
	    });
	    
    </script>
</head>
<body>

	<div id="dialog-modal" title="T&iacutetulo">
		<p>Conteudo do modal.</p>
		http://www.lucaspeperaio.com.br/blog/as-8-melhores-janelas-modais-em-jquery
		http://simplemodal.plasm.it/#examples
		http://www.jacklmoore.com/colorbox/example1/
		http://www.jacklmoore.com/colorbox/example4/ http://fancybox.net/
		http://www.lucaspeperaio.com.br/blog/as-8-melhores-janelas-modais-em-jquery
		http://simplemodal.plasm.it/#examples
		http://www.jacklmoore.com/colorbox/example1/
		http://www.jacklmoore.com/colorbox/example4/ http://fancybox.net/
		http://www.lucaspeperaio.com.br/blog/as-8-melhores-janelas-modais-em-jquery
		http://simplemodal.plasm.it/#examples
		http://www.jacklmoore.com/colorbox/example1/
		http://www.jacklmoore.com/colorbox/example4/ http://fancybox.net/
		http://www.lucaspeperaio.com.br/blog/as-8-melhores-janelas-modais-em-jquery
		http://simplemodal.plasm.it/#examples
		http://www.jacklmoore.com/colorbox/example1/
		http://www.jacklmoore.com/colorbox/example4/ http://fancybox.net/
		http://www.lucaspeperaio.com.br/blog/as-8-melhores-janelas-modais-em-jquery
	</div>

	<button id="create-user">Abrir modal</button>
	<button id="chamada-ajax">Ajax</button>
<div class="header">
    <div class="window"></div>
	  <div class="simple-modal-title"></div>
	</div>
	<div class="wrapper">
		<ul>
			<li class="example-item" id="alert">
				<img src="images/example-1.jpg" width="196" height="147" alt="Example 1">
				<a href="javascript;">Alert</a>
			</li>
			<li class="example-item" id="confirm">
				<img src="images/example-2.jpg" width="196" height="147" alt="Example 2">
				<a href="#">Confirm</a>
			</li>
			<li class="example-item" id="modal">
				<img src="images/example-3.jpg" width="196" height="147" alt="Example 3">
				<a href="#">Modal</a>
			</li>
			<li class="example-item" id="modal-ajax">
				<img src="images/example-4.jpg" width="196" height="147" alt="Example 4">
				<a href="#">Modal Ajax</a>
			</li>
			<li class="example-item" id="modal-image">
				<img src="images/example-5.jpg" width="196" height="147" alt="Example 5">
				<a href="#">Modal Image</a>
			</li>
			<li class="example-item" id="alert-noheader">
				<img src="images/example-6.jpg" width="196" height="147" alt="Example 6">
				<a href="#">No header</a>
			</li>
			<li class="example-item" id="modal-nofooter">
				<img src="images/example-7.jpg" width="196" height="147" alt="Example 7">
				<a href="#">Video embed</a>
			</li>
			<li class="example-item" id="example-eheh">
				<img src="images/example-8.jpg" width="196" height="147" alt="?">
				<a href="#">?</a>
			</li>
			
		</ul>
		<div class="clear"></div>
		<p class="credits">© 2011 <a title="Plasm" href="http://www.plasm.it">Plasm</a>. All Rights Reserved.</p>
	</div>
	<div class="clear"></div>

</body>
</html>
