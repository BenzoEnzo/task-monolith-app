export function validateAccount(mail: string, password: string): Promise<any> {
    return fetch("/api/unauthorized/sign-in", {
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
            return data;
        });
}