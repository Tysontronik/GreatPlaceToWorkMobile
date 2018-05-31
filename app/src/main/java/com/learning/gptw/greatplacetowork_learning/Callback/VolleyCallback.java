package com.learning.gptw.greatplacetowork_learning.Callback;

import com.learning.gptw.greatplacetowork_learning.Models.RestResponseDTO;

/**
 * Volley callback for wait the callback response
 * @param <T>
 */
public interface VolleyCallback<T extends RestResponseDTO> {

    /**
     * Callback generic return
     * @param result
     */
    void onSuccess(T result);

}
