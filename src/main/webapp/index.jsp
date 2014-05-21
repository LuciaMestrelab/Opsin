<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Test editor</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/starter-template.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


<script type="text/javascript" language="javascript"
	src="moleculeEditor/MolEditorExport.nocache.js"></script>


</head>
<body>
	<div class="container">
		<!-- cabeceira -->
		<div class="row">
			<div class="navbar navbar-inverse navbar-fixed-top">
				<div class="container">
					<a href="#" class="navbar-brand">Molecule Editor</a>
				</div>
			</div>
		</div>

		<!-- titulo -->
		<div class="starter">
			<h2>Parser for Systematic IUPAC</h2>
		</div>

		<!-- editor -->
		<div class="row">
			<div class="col-md-6">
			<div class="row">
				<form role="form" action="opsin" method="get">
					<div class="form-group  col-md-6">
						<input id="searchText" name="name" type="text"
							class="form-control" placeholder="Buscar"> <input
							id="formato" type="hidden" name="format">
					</div>
					<button type="button" class="btn btn-primary"
						oncLick="retrieveOpsin()">Search</button>
					<button type="submit" class="btn btn-primary">Call servlet</button>
				</form>
				</div>
				<div id="editor"></div>
			</div>
			<div class="voffset2 col-md-offset-1 col-md-5">
				<form role="form">
					<textarea class="form-control" rows="23" id="areatext"></textarea>
				</form>
			</div>
		</div>

		<!-- botons importar exportar -->
		<div class="row">
			<div class="voffset btn-group col-md-8">
				<button type="button" class="btn btn-default"
					oncLick="importMolfile()">Import</button>
				<button type="button" class="btn btn-default"
					onclick="exportMolfile()">Export</button>
			</div>
		</div>

	</div>

	<!-- FOOTER -->
	<div class="voffset">
		<footer class="navbar navbar-inverse navbar-bottom">
			<div class="container">
				<div class="row">
					<a href="#" class="navbar-brand">Molecule editor</a>
				</div>
			</div>
		</footer>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>

	<script type="text/javascript">
		var a = null;
		//****This function (jscOnLoad) is required.
		//constructor editor mestrelab.JsMoleculeEditor(id,width,height,isEditor)
		// id: id element where editor will be rendered.
		// width,height : Dimensions 
		//isEditor: boolean true or false. (Editor or visor)
		function jscOnLoad() {
			a = new mestrelab.JsMoleculeEditor('editor', 400, 400, 'true');
		}

		function importMolfile() {
			if (a != null)
				//a.importMolfile("Untitled Document-1\n\r  ChemDraw12171217202D\n\r\n\r 41 40  0  0  0  0  0  0  0  0999 V2000\n\r   -3.5720    2.0628    0.0000 N   0  0  0  0  0  0  0  0  0  0  0  0\n\r   -2.8575    1.6504    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r   -2.1431    2.0628    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r   -1.4286    1.6504    0.0000 N   0  0  0  0  0  0  0  0  0  0  0  0\n\r   -0.7141    2.0628    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r    0.0003    1.6504    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r    0.7148    2.0628    0.0000 N   0  0  0  0  0  0  0  0  0  0  0  0\n\r    1.4293    1.6504    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r   -2.1431    2.8864    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n\r    0.0003    0.8268    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n\r    2.1438    2.0628    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r    2.1438    2.8878    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n\r    2.8570    1.6511    0.0000 N   0  0  0  0  0  0  0  0  0  0  0  0\n\r    3.5714    2.0636    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r    4.2859    0.8274    0.0000 N   0  0  0  0  0  0  0  0  0  0  0  0\n\r    4.2859    1.6511    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r    4.2859   -0.8237    0.0000 N   0  0  0  0  0  0  0  0  0  0  0  0\n\r    5.0008   -0.4100    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r    5.0008    0.4155    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r    1.4302   -1.6437    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r    2.1414   -2.0538    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r    2.8544   -1.6437    0.0000 N   0  0  0  0  0  0  0  0  0  0  0  0\n\r    3.5711   -2.0628    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r    4.2859   -1.6492    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r    5.0006    2.0632    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n\r    5.7154   -0.8221    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n\r    3.5711   -2.8878    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n\r    0.7157   -2.0562    0.0000 N   0  0  0  0  0  0  0  0  0  0  0  0\n\r    0.0012   -1.6437    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r   -0.7133   -2.0562    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r   -1.4277   -1.6437    0.0000 N   0  0  0  0  0  0  0  0  0  0  0  0\n\r   -2.1422   -2.0562    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r   -2.8567   -1.6437    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r   -3.5711   -2.0562    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n\r    1.4299   -0.8202    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n\r   -0.7133   -2.8798    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n\r   -2.8567   -0.8202    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n\r   -4.2865    1.6504    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r   -5.0010    2.0628    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n\r   -5.7154    1.6504    0.0000 N   0  0  0  0  0  0  0  0  0  0  0  0\n\r   -4.2865    0.8254    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n\r  2  1  1  0\n\r  3  2  1  0\n\r  4  3  1  0\n\r  5  4  1  0\n\r  6  5  1  0\n\r  7  6  1  0\n\r  8  7  1  0\n\r  3  9  2  0\n\r  6 10  2  0\n\r  8 11  1  0\n\r 11 12  2  0\n\r 11 13  1  0\n\r 14 13  1  0\n\r 16 14  1  0\n\r 15 16  1  0\n\r 17 18  1  0\n\r 18 19  1  0\n\r 15 19  1  0\n\r 20 21  1  0\n\r 21 22  1  0\n\r 22 23  1  0\n\r 23 24  1  0\n\r 17 24  1  0\n\r 16 25  2  0\n\r 18 26  2  0\n\r 23 27  2  0\n\r 28 20  1  0\n\r 29 28  1  0\n\r 30 29  1  0\n\r 31 30  1  0\n\r 32 31  1  0\n\r 33 32  1  0\n\r 34 33  1  0\n\r 20 35  2  0\n\r 30 36  2  0\n\r 33 37  2  0\n\r  1 38  1  0\n\r 38 39  1  0\n\r 39 40  1  0\n\r 38 41  2  0\n\rM  END");
				a.importMolfile($('#areatext').val());
			else
				alert("Variable null");
		}

		function exportMolfile() {
			if (a != null)
				a.exportMolfile(function(molfile) {
					//alert(molfile)
					$('#areatext').val(molfile);
				});
			else
				alert("Variable null");
		}

		function retrieveOpsin() {
			var flickerAPI = "opsin?";
			$.getJSON(flickerAPI, {
				name : $('#searchText').val(),
				format : $('#formato').val()
			}).done(function(data) {
				if(data[0].typeError === -1){
					alert(data[0].message);
				}else{
					for(var i=0; i<data.length; i++) {
						if(data[i].typeMessage == "MOLFILE") {
							if (a!=null) 
								a.importMolfile(data[i].message);
						}
					}
				}
			});
		}

		
	</script>
</body>
</html>