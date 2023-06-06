showServices();

function showServices() {
    document.getElementById("messageText").style.display = "none";
    document.getElementById("addNewService").style.display = "none";

    let servicesContainerNode = document.getElementById("servicesContainer");
    servicesContainerNode.style.display = "block";

    let servicesHeaderHtml = '<div id="serviceHeader" class="serviceContainer">' +
        '<p>Service Name</p>' +
        '<p>Provider Name</p>' +
        '<p>Address</p>' +
        '<p>Availability</p>' +
        '<p>Description</p>' +
        '<p>Contact Number</p>' +
        '</div>';

    servicesContainerNode.innerHTML = servicesHeaderHtml;

	fetch("handyman")
	.then(response=>response.json())
	.then(data=>{
		data.forEach(item => {
			servicesContainerNode.appendChild(createServiceElement(item.serviceName, item.firstName +" "+ item.lastName , item.address, item.availability, item.serviceDesc, item.contactNo));
		})
	});

}


function submitNewService(){
	const newServiceForm = document.getElementById("newServiceForm");
	const formData = new FormData(newServiceForm);

	let providerFullName = formData.get("providerName");
	let contactNo = formData.get("contactNo");
	let address = formData.get("address");
	let availability = formData.get("availability");
	let serviceName = formData.get("serviceName");
	let serviceDescription = formData.get("description");

	let nameArray = providerFullName.split(" ");

	const requestData= {
		firstName: nameArray[0],
		lastName: nameArray[1],
		contactNo: contactNo,
		address: address,
		availability: availability,
		serviceName: serviceName,
		serviceDesc: serviceDescription
	};

	const requestBody = JSON.stringify(requestData);

	fetch("handyman",{
		method: 'POST',
    	headers: {
      		'Content-Type': 'application/json'
    	},
		body: requestBody
	})
	.then(response=>response.json())
	.then(document.getElementById("messageText").innerText="User Registered Successfully")
	//.catch(document.getElementById("messageText").innerText="Could Not Register User")
	.finally(()=>{
		newServiceForm.reset();
		setTimeout(()=>{
			document.getElementById("messageText").innerText="";
		}, 5000)
	}

	)

}


function showNewServiceForm() {
    document.getElementById("messageText").style.display = "block";
    document.getElementById("servicesContainer").style.display = "none"
    document.getElementById("addNewService").style.display = "flex";
}

function createServiceElement(serviceName, providerName, address, availability, description, contactNo) {

    let serviceContainer = document.createElement("div");
    serviceContainer.className = "serviceContainer";

    let serviceNamePElement = document.createElement("p");
    serviceNamePElement.innerText = serviceName;
    serviceNamePElement.className = "serviceName";

    let providerNamePElement = document.createElement("p");
    providerNamePElement.innerText = providerName;

    let addressPElement = document.createElement("p");
    addressPElement.innerText = address;

    let availabilityPElement = document.createElement("p");
    availabilityPElement.innerText = availability==true ? 'Available': 'Not Available';

    let descriptionPElement = document.createElement("p");
    descriptionPElement.innerText = description;

    let contactNoPElement = document.createElement("p");
    contactNoPElement.innerText = contactNo;

    serviceContainer.appendChild(serviceNamePElement);
    serviceContainer.appendChild(providerNamePElement);
    serviceContainer.appendChild(addressPElement);
    serviceContainer.appendChild(availabilityPElement);
    serviceContainer.appendChild(descriptionPElement);
    serviceContainer.appendChild(contactNoPElement);

    return serviceContainer;
}

