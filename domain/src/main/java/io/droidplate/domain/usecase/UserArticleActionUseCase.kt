package io.droidplate.domain.usecase

import io.droidplate.domain.repository.ArticleRepository
import io.reactivex.Completable
import javax.inject.Inject

class UserArticleActionUseCase @Inject constructor(private var repository: ArticleRepository) {

    /**
     * update user reaction on the article
     * @param id -> article id
     * @param action -> user action
     */
    fun updateAction(id: String, action: Boolean): Completable = repository.updateUserAction(id, action)
}