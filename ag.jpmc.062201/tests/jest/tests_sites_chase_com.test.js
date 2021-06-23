const authenticate = require("./authentication");
const {clearSession, dataset, getHttpClient} = require("./up9lib");

describe.each(dataset("data/dataset_36.json"))("test_36_get_content_Creatives_Public_Heroes_heroeId_March_cid", (cid, heroeId) => {
    it("test_36_get_content_Creatives_Public_Heroes_heroeId_March_cid", () => {
        clearSession();

        // GET https://sites.chase.com/content/Creatives/Public/Heroes/{heroeId}/March/{cid} (endp 36)
        const sites_chase_com = getHttpClient("https://sites.chase.com", authenticate);
        return sites_chase_com.fetch("/content/Creatives/Public/Heroes/" + heroeId + "/March/" + cid)
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
        });
    });
});

describe.each(dataset("data/dataset_37.json"))("test_37_get_content_Creatives_Public_Tiles_tileId_March_cid", (cid, tileId) => {
    it("test_37_get_content_Creatives_Public_Tiles_tileId_March_cid", () => {
        clearSession();

        // GET https://sites.chase.com/content/Creatives/Public/Tiles/{tileId}/March/{cid} (endp 37)
        const sites_chase_com = getHttpClient("https://sites.chase.com", authenticate);
        return sites_chase_com.fetch("/content/Creatives/Public/Tiles/" + tileId + "/March/" + cid)
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
        });
    });
});

describe.each(dataset("data/dataset_38.json"))("test_38_get_content_Creatives_Public_Triplets_tripletId_July_cid", (cid, tripletId) => {
    it("test_38_get_content_Creatives_Public_Triplets_tripletId_July_cid", () => {
        clearSession();

        // GET https://sites.chase.com/content/Creatives/Public/Triplets/{tripletId}/July/{cid} (endp 38)
        const sites_chase_com = getHttpClient("https://sites.chase.com", authenticate);
        return sites_chase_com.fetch("/content/Creatives/Public/Triplets/" + tripletId + "/July/" + cid)
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
        });
    });
});

describe.each(dataset("data/dataset_40.json"))("test_40_get_content_Creatives_Public_Triplets_tripletId_May_cid", (cid, tripletId) => {
    it("test_40_get_content_Creatives_Public_Triplets_tripletId_May_cid", () => {
        clearSession();

        // GET https://sites.chase.com/content/Creatives/Public/Triplets/{tripletId}/May/{cid} (endp 40)
        const sites_chase_com = getHttpClient("https://sites.chase.com", authenticate);
        return sites_chase_com.fetch("/content/Creatives/Public/Triplets/" + tripletId + "/May/" + cid)
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
        });
    });
});

describe.each(dataset("data/dataset_39.json"))("test_39_get_content_Creatives_Public_Triplets_tripletId_November_cid", (cid, tripletId) => {
    it("test_39_get_content_Creatives_Public_Triplets_tripletId_November_cid", () => {
        clearSession();

        // GET https://sites.chase.com/content/Creatives/Public/Triplets/{tripletId}/November/{cid} (endp 39)
        const sites_chase_com = getHttpClient("https://sites.chase.com", authenticate);
        return sites_chase_com.fetch("/content/Creatives/Public/Triplets/" + tripletId + "/November/" + cid)
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
        });
    });
});
