const authenticate = require("./authentication");
const {CSSselect, clearSession, getHttpClient} = require("./up9lib");

it("test_056_get_personal_checking", () => {
    clearSession();

    // GET https://personal.chase.com/personal/checking (endp 56)
    const personal_chase_com = getHttpClient("https://personal.chase.com", authenticate);
    return personal_chase_com.fetch("/personal/checking")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("div#jsEnabled div.generic-modal.card-modal div.modal-dialog div.modal-content div.row.modal-heading h3 sup.sm-fix", text)).toContain("SM");
        expect(CSSselect("html head title", text)).toContain("Chase Checking Accounts: Compare & Apply Today | Chase");
    })
    .then((data) => {
    });
});
