package ProjetGenieLogiciel.isepval.models.Dtos;


import ProjetGenieLogiciel.isepval.models.Post;

public class PostIsLiked {
    private Post post;
    private boolean isLiked;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}