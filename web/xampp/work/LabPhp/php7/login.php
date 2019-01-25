<?php
	session_start();
?>
	<form method="post" action="login_action.php">
	<fieldset>
	<legend>Login</legend>
	<p><label for="username">Username:</label> <input type="text" name="username" id="username" /></p>
	<p><label for="password">Password:</label> <input type="password" name="password" id="password" /></p>
	<p class="center"><input value="Login" type="submit" class="center" /></p>
	</fieldset>
	</form>

<?php 
?>