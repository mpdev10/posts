package pl.mpakula.posts.provider;

import java.util.List;

interface CommentRepository {

    List<Comment> findAll();

}
