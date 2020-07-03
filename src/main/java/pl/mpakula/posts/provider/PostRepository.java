package pl.mpakula.posts.provider;

import java.util.List;

interface PostRepository {

    List<Post> findAll();

}
