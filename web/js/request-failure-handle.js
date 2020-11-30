function handleRequestFailure(error) {
    switch (error.status) {
        case 402:
            alert("Invalid username or password!");
            break;
        default:
            alert("something went wrong");
    }
    location.reload();
}