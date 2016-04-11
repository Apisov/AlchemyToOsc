/**
 * Copyright 2015 IBM Corp. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package models;

import com.google.gson.annotations.SerializedName;

public class Url extends GenericModel {

    /** The article. */
    @SerializedName("url")
    private Article article;

    /**
     * Gets the article.
     *
     * @return the article
     */
    public Article getArticle() {
        return article;
    }

    /**
     * Sets the article.
     *
     * @param article the article to set
     */
    public void setArticle(Article article) {
        this.article = article;
    }

}
