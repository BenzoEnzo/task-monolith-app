export function validateAccount(mail: string, password: string): Promise<any> {
    return fetch("/api/user/sign-in", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ mail, password })
    })
        .then(response => {
            const authToken = response.headers.get('AUTHORIZATION');
            if (authToken) {
                sessionStorage.setItem("authToken", authToken);
            }

            if (response.ok && response.headers.get("content-type")?.includes("application/json")) {
                return response.json();
            } else {
                throw new Error('Server response is not JSON or response returned an error');
            }
        })
        .then(data => {
            sessionStorage.setItem("id", data.body.id);
            sessionStorage.setItem("photoId", data.body.photoId);
            return data;
        });
}

export function registration(mail: string, password: string): Promise<any> {
    return fetch("/api/user/sign-up", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ mail, password })
    }).then(response => {
        if(!response.ok){
            throw new Error('Registration failed');
        }
    });
}