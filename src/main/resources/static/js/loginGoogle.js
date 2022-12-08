function onSignIn() {
    google.accounts.id.initialize({
        client_id: "976906053956-l05prnqd30qlqs0pfvl2vt7ra8lr0ia1.apps.googleusercontent.com",
        callback: handleCredentialResponse
    });
    google.accounts.id.prompt();
}
function handleCredentialResponse(response) {
    var profile = jwt_decode(response.credential);
	console.log("ID: " + profile.sub);
	console.log('Name: ' + profile.name);
    console.log("Email: " + profile.email);
    
    let googleForm = document.createElement("form");
	googleForm.setAttribute("charset", "UTF-8");
	googleForm.setAttribute("method", "post");
	googleForm.setAttribute("action", "apiLogin");
	document.body.appendChild(googleForm);
	
	const input1 = document.createElement('input');
	input1.type = "hidden"; input1.name = "id"; input1.value = "google_" + profile.sub;
	googleForm.append(input1);
	const input2 = document.createElement('input');
	input2.type = "hidden"; input2.name = "name"; input2.value = profile.name;
	googleForm.append(input2);
	const input3 = document.createElement('input');
	input3.type = "hidden"; input3.name = "email"; input3.value = profile.email;
	googleForm.append(input3);
	
	googleForm.submit();
}