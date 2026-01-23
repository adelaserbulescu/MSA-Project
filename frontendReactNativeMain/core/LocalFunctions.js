function getYouTubeThumbnail(url) {
    try {
        const id = url.split("/embed/")[1]?.split("?")[0];
        return id ? `https://img.youtube.com/vi/${id}/hqdefault.jpg` : null;
    } catch {
        return null;
    }
}

