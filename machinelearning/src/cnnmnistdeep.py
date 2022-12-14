# -*- coding: utf-8 -*-
import tensorflow as tf
import random
from tensorflow.examples.tutorials.mnist import input_data
# set variables
tf.set_random_seed(777)

def main():

    # set data
    mnist = input_data.read_data_sets("MNIST_data/", one_hot=True)

    # set hyper params
    f_learning_rate = 0.001
    n_epochs = 15
    n_batch_size = 100

    # set place holders
    t_X = tf.placeholder(tf.float32, [None, 784])
    t_X_img = tf.reshape(t_X, [-1, 28, 28, 1])
    t_Y = tf.placeholder(tf.float32, [None, 10])
    t_K = tf.placeholder(tf.float32)

    # set W, L, b nodes
    # L1 ImgIn shape=(?, 28, 28, 1)
    # conv -> (?, 28, 28, 32)
    # pool -> (?, 14, 14, 32)
    t_W1 = tf.Variable(tf.random_normal([3, 3, 1, 32], stddev=0.01))
    t_L1 = tf.nn.conv2d(t_X_img, t_W1, strides=[1, 1, 1, 1], padding='SAME')
    t_L1 = tf.nn.relu(t_L1)
    t_L1 = tf.nn.max_pool(t_L1, ksize=[1, 2, 2, 1], strides=[1, 2, 2, 1], padding='SAME')
    t_L1 = tf.nn.dropout(t_L1, keep_prob=t_K)

    # L2 ImgIn shape=(?, 14, 14, 32)
    # conv -> (?, 14, 14, 64)
    # pool -> (?,  7,  7, 64)
    t_W2 = tf.Variable(tf.random_normal([3, 3, 32, 64], stddev=0.01))
    t_L2 = tf.nn.conv2d(t_L1, t_W2, strides=[1, 1, 1, 1], padding='SAME')
    t_L2 = tf.nn.relu(t_L2)
    t_L2 = tf.nn.max_pool(t_L2, ksize=[1, 2, 2, 1], strides=[1, 2, 2, 1], padding='SAME')
    t_L2 = tf.nn.dropout(t_L2, keep_prob=t_K)

    # L3 ImgIn shape=(?, 7, 7, 64)
    #    Conv      ->(?, 7, 7, 128)
    #    Pool      ->(?, 4, 4, 128)
    #    Reshape   ->(?, 4 * 4 * 128) # Flatten them for FC
    t_W3 = tf.Variable(tf.random_normal([3, 3, 64, 128], stddev=0.01))
    t_L3 = tf.nn.conv2d(t_L2, t_W3, strides=[1, 1, 1, 1], padding='SAME')
    t_L3 = tf.nn.relu(t_L3)
    t_L3 = tf.nn.max_pool(t_L3, ksize=[1, 2, 2, 1], strides=[1, 2, 2, 1], padding='SAME')
    t_L3 = tf.nn.dropout(t_L3, keep_prob=t_K)
    t_L3_flat = tf.reshape(t_L3, [-1, 128 * 4 * 4])    
    
    # L4 FC 4x4x128 inputs -> 625 outputs
    t_W4 = tf.get_variable("W4", shape=[128 * 4 * 4, 625], initializer=tf.contrib.layers.xavier_initializer())
    t_b4 = tf.Variable(tf.random_normal([625]))
    t_L4 = tf.nn.relu(tf.matmul(t_L3_flat, t_W4) + t_b4)
    t_L4 = tf.nn.dropout(t_L4, keep_prob=t_K)

    # L5 Final FC 625 inputs -> 10 outputs
    t_W5 = tf.get_variable("W5", shape=[625, 10], initializer=tf.contrib.layers.xavier_initializer())
    t_b5 = tf.Variable(tf.random_normal([10]))
    t_H = tf.matmul(t_L4, t_W5) + t_b5

    # set train node
    t_C = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits=t_H, labels=t_Y))
    t_T = tf.train.AdamOptimizer(learning_rate=f_learning_rate).minimize(t_C)

    # launch nodes
    with tf.Session() as sess:
        print("started machine learning")
        sess.run(tf.global_variables_initializer())
        for n_epoch in range(n_epochs):
            f_avg_cost = 0
            # 55000 / 100 = 550
            n_total_batch = int(mnist.train.num_examples / n_batch_size)
            for i in range(n_total_batch):
                l_X, l_Y = mnist.train.next_batch(n_batch_size)
                f_cost, _ = sess.run([t_C, t_T], feed_dict={t_X: l_X, t_Y: l_Y, t_K: 0.7})
                f_avg_cost += f_cost / n_total_batch
                # if i % 10 == 0:
                #     print(f'  batch: {i:8d}, cost:{f_cost:10.9f}, f_avg_cost: {f_avg_cost:10.9f}')
            print(f'epoch: {n_epoch:10d}, cost: {f_avg_cost:10.9f}')
        print("ended machine learning")

        # Test model and check accuracy
        t_pred = tf.equal(tf.argmax(t_H, 1), tf.argmax(t_Y, 1))
        t_accu = tf.reduce_mean(tf.cast(t_pred, tf.float32))
        print('Accuracy:', sess.run(t_accu, 
            feed_dict={t_X: mnist.test.images, t_Y: mnist.test.labels, t_K: 1.0}))

        # Get one and predict
        n_r = random.randint(0, mnist.test.num_examples - 1)
        print("Label: ", sess.run(tf.argmax(mnist.test.labels[n_r:n_r + 1], 1)))
        print("Prediction: ", sess.run(tf.argmax(t_H, 1), 
            feed_dict={t_X: mnist.test.images[n_r:n_r + 1], t_K: 1.0}))

# plt.imshow(mnist.test.images[r:r + 1].
#           reshape(28, 28), cmap='Greys', interpolation='nearest')
# plt.show()
            
if __name__ == "__main__":
    main()
