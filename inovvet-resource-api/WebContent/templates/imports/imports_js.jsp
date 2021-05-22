<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<!-- jQuery -->
<script src="${rootweb }/assets/sb_admin_2/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${rootweb }/assets/sb_admin_2/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${rootweb }/assets/sb_admin_2/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="${rootweb }/assets/sb_admin_2/dist/js/sb-admin-2.js"></script>

<!-- MASCARA -->
<script src="${rootweb }/assets/jquery_mask_plugin-master/dist/jquery.mask.min.js"></script>

<!-- BootStrap-Dialog  -->
<script src="${rootweb }/assets/dialog/dist/js/bootstrap-dialog.min.js"></script>



<script type="text/javascript">
	/* 
	 EXECUTADO APÓS A CARGA DA PAGINA 
	 Trabalhando com JQuery para manipular os componentes.
	 # pega os elementos pelo ID - $("#nome")
	 . pega os elementos por CLASS - $(".bloqueado")
	 attr serve para adicionar atributos em tempo de execução
	 */
	$(document).ready(function() {
		//Bloqueando campos
		$('.bloqueado').prop('disabled', true);

		//criando a estilização para campos obrigatórios
		$('.obrigatorio').prop('required', 'required');
		$('.obrigatorio').css('border-color', 'orange');
		
		//definindo mascaras
		$('.dinheiro').mask('000.000.000.000.000,00',{
			reverse: true
		});

	});
</script>

