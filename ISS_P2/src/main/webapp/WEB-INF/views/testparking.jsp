<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js">
	</script>
</head>
<body>

<form action="#" id="enviar">
   <h2> Simulaci�n de los datos de barrera y matricula: </h2>
   Parking Id: <input type="text" name="parkingId" id="parkingId" maxlength="1" required> 
   Matricula: <input type="text" name="matricula" id="matricula" maxlength="8" required>
   <br><br>
   <input type="submit" value="Envia Datos"> 
</form>
<script type="text/javascript">
	$('#enviar').submit(function(evento){
		var urlrest = 'registroMatricula';
		$.ajax({
			url: urlrest,
			type: 'POST',
			contentType: "application/json",
		    data: JSON.stringify({parkingId:$('#parkingId').val(),matricula:$('#matricula').val()}),
		    success: function(){alert('Los datos se han recibido con �xito');},
		    error: function(){alert('Error en el env�o de los datos');}
		});
		evento.preventDefault(); //Evita que se ejecute la petici�n GET del form
	})
</script>

</body>
</html>