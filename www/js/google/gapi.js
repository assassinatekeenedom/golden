var clientId = '707106328681.apps.googleusercontent.com';
var apiKey = 'AIzaSyAXKYXe6Z7EsjZ0CmEb5dwLYo5XqeKQcOc';
var scopes = 'https://www.googleapis.com/auth/analytics.readonly';

// This function is called after the Client Library has finished loading
function handleClientLoad() {
    // 1. Set the API Key
    gapi.client.setApiKey(apiKey);
    // 2. Call the function that checks if the user is Authenticated. This is defined in the next section
    window.setTimeout(checkAuth, 1);
}

function checkAuth() {
    // Call the Google Accounts Service to determine the current user's auth status.
    // Pass the response to the handleAuthResult callback function
    gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: true}, handleAuthResult);
}

function handleAuthResult(authResult) {
    if (authResult) {
        console.log("The user has authorized access; Load the Analytics Client.");
        loadAnalyticsClient();
    } else {
        console.warn("User has not Authenticated and Authorized");
        handleUnAuthorized();
    }
}


var authorizeButton = document.createElement("button");
authorizeButton.innerHTML = "... Login ...";
document.addEventListener("mousedown",checkAuth,true);
var makeApiCallButton = document.createElement("button");
area.innerHTML="";
area.appendChild(authorizeButton);
document.body.appendChild(area);
// Authorized user
function handleAuthorized() {
    // Show the 'Get Visits' button and hide the 'Authorize' button
    console.log("Authorized; removing authorization button");
    area.removeChild(authorizeButton);
    area.appendChild(makeApiCallButton);
    // When the 'Get Visits' button is clicked, call the makeAapiCall function
    
    makeApiCallButton.onclick = makeApiCall;
}


// Unauthorized user
function handleUnAuthorized() {
    // When the 'Authorize' button is clicked, call the handleAuthClick function
    authorizeButton.onclick = handleAuthClick;

    function handleAuthClick(event) {
        gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: false}, handleAuthResult);
        return false;
    }}

function loadAnalyticsClient() {
    // Load the Analytics client and set handleAuthorized as the callback function
    gapi.client.load('analytics', 'v3', handleAuthorized);
}

function makeApiCall() {
    queryAccounts();
}

function queryAccounts() {
    console.log('Querying Accounts.');

    // Get a list of all Google Analytics accounts for this user
    gapi.client.analytics.management.accounts.list().execute(handleAccounts);
}

function handleAccounts(results) {
    if (!results.code) {
        if (results && results.items && results.items.length) {

            // Get the first Google Analytics account
            var firstAccountId = results.items[0].id;

            // Query for Web Properties
            queryWebproperties(firstAccountId);
            
        } else {
            console.log('No accounts found for this user.')
        }
    } else {
        console.log('There was an error querying accounts: ' + results.message);
    }
}

function queryWebproperties(accountId) {
    console.log('Querying Webproperties.');

    // Get a list of all the Web Properties for the account
    gapi.client.analytics.management.webproperties.list({'accountId': accountId}).execute(handleWebproperties);
}

function handleWebproperties(results) {
    if (!results.code) {
        if (results && results.items && results.items.length) {

            // Get the first Google Analytics account
            var firstAccountId = results.items[0].accountId;

            // Get the first Web Property ID
            var firstWebpropertyId = results.items[0].id;

            // Query for Views (Profiles)
            queryProfiles(firstAccountId, firstWebpropertyId);

        } else {
            console.log('No webproperties found for this user.');
        }
    } else {
        console.log('There was an error querying webproperties: ' + results.message);
    }
}

function queryProfiles(accountId, webpropertyId) {
    console.log('Querying Views (Profiles).');

    // Get a list of all Views (Profiles) for the first Web Property of the first Account
    gapi.client.analytics.management.profiles.list({
        'accountId': accountId,
        'webPropertyId': webpropertyId
    }).execute(handleProfiles);
}

function handleProfiles(results) {
    if (!results.code) {
        if (results && results.items && results.items.length) {

            // Get the first View (Profile) ID
            var firstProfileId = results.items[0].id;

            // Step 3. Query the Core Reporting API
            queryCoreReportingApi(firstProfileId);

        } else {
            console.log('No views (profiles) found for this user.');
        }
    } else {
        console.log('There was an error querying views (profiles): ' + results.message);
    }
}

function queryCoreReportingApi(profileId) {
    console.log('Querying Core Reporting API.');

    // Use the Analytics Service Object to query the Core Reporting API
    gapi.client.analytics.data.ga.get({
        'ids': 'ga:' + profileId,
        'start-date': '2012-01-01',
        'end-date': '2014-01-01',
        'metrics': 'ga:visits'
    }).execute(handleCoreReportingResults);
}

function handleCoreReportingResults(results) {
    if (results.error) {
        console.log('There was an error querying core reporting API: ' + results.message);
    } else {
        printResults(results);
    }
}

function printResults(results) {
    if (results.rows && results.rows.length) {
        console.warn(results);
        console.log('View (Profile) Name: ', results.profileInfo.profileName);
        console.log('Total Visits: ', results.rows[0][0]);
    } else {
        console.log('No results found');
    }
}