(function main() {
    const apiResult = document.getElementById("jsApiResult");
    /**
     *
     * @param endpoint {string}
     * @param payload {object}
     * @returns {Promise<string|*>}
     */
    async function send(endpoint, payload) {
        const url = `http://localhost:8080/api/v1/${endpoint}?` + new URLSearchParams(payload);

        try {
            const responseFetched = await fetch(url, {
                method: "POST",
            });
            const response = await responseFetched.json();
            apiResult.textContent = JSON.stringify(response);
            return response["result"];

        } catch (error) {
            console.error(error);
            apiResult.textContent = error;
            return "";
        }
    }

    /**
     * Registers a html block into a Calculator operation
     * @param settings {object}
     */
    function registerCalculatorOperation(settings) {
        const htmlValueA = document.getElementById(settings.htmlValueA);
        const htmlValueB = document.getElementById(settings.htmlValueB);
        const htmlResult = document.getElementById(settings.htmlResult);
        const htmlSubmit = document.getElementById(settings.htmlSubmit);

        if (!htmlValueA || !htmlValueB || !htmlResult || !htmlSubmit) {
            const errorMessage = `"Could not initialize Calculator ${settings.name}!"`;
            console.error(errorMessage);
            alert(errorMessage);
        }

        htmlSubmit.addEventListener("click", async _ => {
            const payload = {
                valueA: htmlValueA.value,
                valueB: htmlValueB.value,
            }

            let result = await send(settings.endpoint, payload);
            if (!result) {
                result = "ERROR";
            }
            htmlResult.textContent = result;
        });
    }

    (function initAddition() {
        registerCalculatorOperation({
            name: "Addition",
            htmlValueA: "jsAddA",
            htmlValueB: "jsAddB",
            htmlResult: "jsAddResult",
            htmlSubmit: "jsSubmitAddition",
            endpoint: "add",
        });
    }());

    (function initSubtraction() {
        registerCalculatorOperation({
            name: "Subtraction",
            htmlValueA: "jsSubtractionA",
            htmlValueB: "jsSubtractionB",
            htmlResult: "jsSubtractionResult",
            htmlSubmit: "jsSubmitSubtraction",
            endpoint: "subtract",
        });
    }());

    (function initMultiplication() {
        registerCalculatorOperation({
            name: "Multiplication",
            htmlValueA: "jsMultiplierA",
            htmlValueB: "jsMultiplierB",
            htmlResult: "jsMultiplierResult",
            htmlSubmit: "jsSubmitMultiplication",
            endpoint: "multiply",
        });
    }());

    (function initDivision() {
        registerCalculatorOperation({
            name: "Division",
            htmlValueA: "jsDivisionA",
            htmlValueB: "jsDivisionB",
            htmlResult: "jsDivisionResult",
            htmlSubmit: "jsSubmitDivision",
            endpoint: "divide",
        });
    }());

}());