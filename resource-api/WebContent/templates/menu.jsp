<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<div class="navbar-default sidebar" role="navigation">
<div class="sidebar-nav navbar-collapse">
	<ul class="nav" id="side-menu">
		
		<li>
			<a href="index.html">
				<i class="fa fa-dashboard fa-fw"></i>
				Home
			</a>
		</li>
		
		<li>
			<a href="tables.html">
			<a href="${rootweb }/unidadeAction.do?method=abrirTela"/>
				<i class="fa fa-cubes fa-fw"></i>
				Unidade
			</a>
		</li>
		<li>
			<a href="tables.html">
			<a href="${rootweb }/categoriaAction.do?method=abrirTela"/>
				<i class="fa fa-cubes fa-fw"></i>
				Categoria
			</a>
		</li>
		
		<li>
			<a href="tables.html">
			<a href="${rootweb }/produtoAction.do?method=abrirTela"/>
				<i class="fa fa-cubes fa-fw"></i>
				Produto
			</a>
		</li>
		
	</ul>
</div>
<!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->