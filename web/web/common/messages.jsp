<%-- Error Messages --%>
<logic:messagesPresent>
	<div class="error"><html:messages id="error">
			${error}<br />
	</html:messages></div>
</logic:messagesPresent>

<%-- Success Messages --%>
<logic:messagesPresent message="true">
	<div class="message" id="message"><html:messages id="message" message="true">
			${message}<br />
	</html:messages></div>

	<script type="text/javascript">
		new Effect.Highlight('message');
		window.setTimeout("Effect.DropOut('message')", 1000);
	</script>
</logic:messagesPresent>
